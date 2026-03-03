package com.phetolo.Financeapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.dto.TransactionDTO;
import com.phetolo.Financeapi.model.Transaction;
import com.phetolo.Financeapi.service.TransactionServices;

@RestController
@RequestMapping("users/{userId}/transactions")
public class TransactionController {
	private TransactionServices transactionService;
	
	public TransactionController(TransactionServices transactionService) {
		this.transactionService = transactionService;
	}
	
	@GetMapping
	public List<TransactionDTO> getAll(@PathVariable Long userId){
		return transactionService.getUserTransactions(userId);
	}
	
	@PostMapping
	public TransactionDTO addTransactions(@PathVariable Long userId,@RequestBody TransactionDTO t) {
		return transactionService.addTransaction(userId, t);
	}
	
	@GetMapping("/balance")
	public Double getBalannce(@PathVariable Long userId) {
		return transactionService.calculateBalance(userId);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTransaction(@PathVariable Long id,@PathVariable Long userId) {
		
		 transactionService.deleteTransaction(userId, id);
	}
}
