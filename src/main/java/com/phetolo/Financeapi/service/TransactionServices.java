package com.phetolo.Financeapi.service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phetolo.Financeapi.enums.TransactionType;
import com.phetolo.Financeapi.model.Transaction;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.repository.TransactionRepository;
import com.phetolo.Financeapi.repository.UserRepository;
@Service
public class TransactionServices {
	private TransactionRepository Trepo;
	private UserRepository Urepo;
	private List<Transaction> userTransactions;
	
	public TransactionServices(TransactionRepository Trepo, UserRepository Urepo) {
		this.Trepo = Trepo;
		this.Urepo = Urepo;
	}
	
	public Transaction addTransaction(Long id, Transaction transaction) {
		Optional<User> user = Urepo.findById(id);
		
		transaction.setUser(user.get());
		return Trepo.save(transaction);
		
	}
	
	public List<Transaction> getUserTransactions(Long userId){
		return( userTransactions = Trepo.findByUserId(userId));
	}
	
	public List<Transaction> getTransactionsByMonth(Long userId, YearMonth month) {
	    int year = month.getYear();
	    int m = month.getMonthValue();
	    return Trepo.findByUserIdAndMonth(userId, m, year);
	}
	
	public Double calculateTotalIncome(Long userId) {

		return Trepo.findByuserIdAndType(userId, TransactionType.INCOME).stream().mapToDouble(t->t.getAmount().doubleValue()).sum();
	}
	
	public Double calculateTotalExpense(Long userId) {

		return Trepo.findByuserIdAndType(userId, TransactionType.EXPENSE).stream().mapToDouble(t->t.getAmount().doubleValue()).sum();
	}
	
	public Double calculateBalance(Long userId) {
		return (calculateTotalIncome(userId)-calculateTotalExpense(userId));
	}
	
	public void deleteTransaction(Long userId,Long transactionId) {
		Optional<Transaction> transaction = Trepo.findById(transactionId);
		if(transaction.get().getUser().getId()!=userId) {
			throw new RuntimeException("Unauthorized");
		}
		Trepo.delete(transaction.get());
	}
}
