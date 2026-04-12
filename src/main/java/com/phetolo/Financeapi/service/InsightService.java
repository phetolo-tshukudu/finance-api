package com.phetolo.Financeapi.service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phetolo.Financeapi.dto.InsightResponsedto;
import com.phetolo.Financeapi.enums.TransactionType;
import com.phetolo.Financeapi.exception.TransactionNotFoundException;
import com.phetolo.Financeapi.model.Budget;
import com.phetolo.Financeapi.model.Transaction;
import com.phetolo.Financeapi.repository.BudgetRepository;
import com.phetolo.Financeapi.repository.TransactionRepository;

@Service
public class InsightService {
	
	private BudgetRepository Brepo;
	private TransactionRepository Trepo;
	
	
	public InsightService(BudgetRepository brepo, TransactionRepository trepo) {
		Brepo = brepo;
		Trepo = trepo;
	}

	public double calculateTotalSpending(Long userId) {
		List<Transaction> transactions = Trepo.findByUser_IdAndType(userId,TransactionType.EXPENSE);
		System.out.println("In the spending method");
		if(transactions== null ) 
			throw new TransactionNotFoundException("No Transactions for the user : "+userId);
		
		double spend = transactions.stream().mapToDouble(t->t.getAmount().doubleValue()).sum();
		
		return spend;
	}
	
	public double calculateTotalIncome(Long userId) {
		List<Transaction> transactions = Trepo.findByUser_IdAndType(userId,TransactionType.INCOME);
		System.out.println("In the income method");
		if(transactions== null ) 
			throw new TransactionNotFoundException("No Transactions for the user : "+userId);
		
		double income = transactions.stream().mapToDouble(t->t.getAmount().doubleValue()).sum();
		return income;
	}
	
	public double calculateNetBalance(Long userId) {
		return calculateTotalIncome(userId)-calculateTotalSpending(userId);
	}
	
	public InsightResponsedto getInsights(Long userId) {
		return new InsightResponsedto(calculateTotalSpending(userId),calculateTotalIncome(userId),calculateNetBalance(userId),calculateSpendChange(userId),checkSpend(calculateSpendChange(userId)),buildwarnings(calculateTotalSpending(userId),userId));
	}
	public double calculateSpendChange(Long userId) {
		YearMonth currMont = YearMonth.now(); 
		List<Transaction> CurrentTransactions = Trepo.findByUser_IdAndDateBetween(userId, currMont.atDay(1), currMont.atEndOfMonth());
		YearMonth prevMont =currMont.minusMonths(1);
		List<Transaction> prevTransactions = Trepo.findByUser_IdAndDateBetween(userId, prevMont.atDay(1), prevMont.atEndOfMonth());
		double currSpend = getTotalSpend(CurrentTransactions);
		double prevSpend = getTotalSpend(prevTransactions);
		return ((prevSpend-currSpend)/(prevSpend));
	}

	private double getTotalSpend(List<Transaction> transactions) {
		// TODO Auto-generated method stub
		double total = 0.0;
		for(Transaction t: transactions) {
			if(t.getType().equals(TransactionType.EXPENSE) && t!=null) {
				total+=t.getAmount().doubleValue();
			}
		}
		return total;
	}
	
	public String checkSpend(double spendChange) {
		if(spendChange > 0) {
			return "Your total spending increased by " + (spendChange*100) + "% from last month";
		}else if(spendChange < 0) {
			return "Good job. Your spending decreased by " + (-1*spendChange*100) + "& from last month";
		}else {
			return "Your spending has not changed since last month";
		}
	
	}
	
	public String buildwarnings(double totalSpend, Long userId) {
		Optional<Budget> budget = Brepo.findByUser_Id(userId);
		if(budget==null || budget.isEmpty() ) 
			throw new RuntimeException("No budget found for :"+userId );
		
		double limit = budget.get().getMonthlyLimit().doubleValue();
		double diff = limit - totalSpend;
		if(diff > limit/2) {
			return "Your have used more than 50% of your monthly spending limit";
			
		}else if(diff < limit/2) {
			return "Your have used less than 50% of your monthly spending limit";
			
		}else {
			return  "Your have used 50% of your monthly spending limit";
		}
	}
}
