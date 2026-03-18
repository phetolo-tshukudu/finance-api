package com.phetolo.Financeapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phetolo.Financeapi.dto.TransactionDTO;
import com.phetolo.Financeapi.enums.TransactionType;
import com.phetolo.Financeapi.exception.BudgetExceededException;
import com.phetolo.Financeapi.exception.TransactionNotFoundException;
import com.phetolo.Financeapi.exception.UnauthorizedUserException;
import com.phetolo.Financeapi.exception.UserNotFoundException;
import com.phetolo.Financeapi.mapper.TransactionMapper;
import com.phetolo.Financeapi.model.Budget;
import com.phetolo.Financeapi.model.Transaction;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.repository.BudgetRepository;
import com.phetolo.Financeapi.repository.TransactionRepository;
import com.phetolo.Financeapi.repository.UserRepository;
@Service
public class TransactionServices {
	private TransactionRepository Trepo;
	private UserRepository Urepo;
	private BudgetRepository Brepo;
	private List<Transaction> userTransactions;
	
	public TransactionServices(TransactionRepository Trepo, UserRepository Urepo,BudgetRepository Brepo) {
		this.Trepo = Trepo;
		this.Urepo = Urepo;
		this.Brepo=Brepo;
	}
	
	public TransactionDTO addTransaction(Long userId, TransactionDTO transaction) throws BudgetExceededException {
		Optional<User> user = Urepo.findById(userId);
		Optional<Budget> b = Brepo.findByUser_Id(userId);
		
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("Could not find the user");
		}
		
		Transaction t = TransactionMapper.mapToEntity(transaction);
		if(!b.isPresent()) {
			t.setUser(user.get());
			Budget budget = new Budget("Default budget, new user.",new BigDecimal(500),YearMonth.now(),user.get());
			Brepo.save(budget);
		}
		else if(transaction.getType().equals(TransactionType.EXPENSE)){
			double total = calculateTotalExpense(user.get().getId()) + transaction.getAmount().doubleValue();
			if(total > b.get().getMonthlyLimit().doubleValue())
				throw new BudgetExceededException("Budget exceeded for "+ "User: "+ user.get().getEmail());
		}
		
		t.setDate(LocalDate.now());
		return TransactionMapper.mapToDto(Trepo.save(t));
		
	}
	
	public List<TransactionDTO> getUserTransactions(Long userId) throws TransactionNotFoundException{
		if(!Trepo.existsByUser_Id(userId)) {
			throw new TransactionNotFoundException("Transaction does not exist!");
		}
		userTransactions = Trepo.findByUser_Id(userId);
		return userTransactions.stream().map(TransactionMapper::mapToDto).toList();
	}
	
	public List<TransactionDTO> getTransactionsByMonth(Long userId, YearMonth month) {
		if(!Trepo.existsByUser_Id(userId)) {
			throw new TransactionNotFoundException("Transaction does not exist!");
		}
	    int year = month.getYear();
	    int m = month.getMonthValue();
	    return Trepo.findByUser_IdAndMonth(userId, m, year).stream().map(TransactionMapper::mapToDto).toList();
	}
	
	public Double calculateTotalIncome(Long userId) {

		return Trepo.findByUser_IdAndType(userId, TransactionType.INCOME).stream().mapToDouble(t->t.getAmount().doubleValue()).sum();
	}
	
	public Double calculateTotalExpense(Long userId) {

		return Trepo.findByUser_IdAndType(userId, TransactionType.EXPENSE).stream().mapToDouble(t->t.getAmount().doubleValue()).sum();
	}
	
	public Double calculateBalance(Long userId) {
		return (calculateTotalIncome(userId)-calculateTotalExpense(userId));
	}
	
	public void deleteTransaction(Long userId,Long transactionId)  {
		if(!Trepo.existsById(transactionId)) {
			throw new TransactionNotFoundException("Transaction not found for id: "+transactionId);
		}
		Optional<Transaction> transaction = Trepo.findById(transactionId);
		if(transaction.get().getUser().getId()!=userId) {
			throw new UnauthorizedUserException("Unauthorized");
		}
		Trepo.delete(transaction.get());
	}
}
