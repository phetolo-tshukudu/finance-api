package com.phetolo.Financeapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.dto.TransactionDTO;
import com.phetolo.Financeapi.exception.BudgetExceededException;
import com.phetolo.Financeapi.payload.ApiResponse;
import com.phetolo.Financeapi.service.TransactionServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users/{userId}/transactions")
public class TransactionController {
	private TransactionServices transactionService;
	
	public TransactionController(TransactionServices transactionService) {
		this.transactionService = transactionService;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<TransactionDTO>>> getAll(@PathVariable Long userId){
		ApiResponse<List<TransactionDTO>> response = new ApiResponse<>(HttpStatus.FOUND.value(), "Transactions found", transactionService.getUserTransactions(userId));
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<TransactionDTO>> addTransactions(@PathVariable Long userId,@Valid @RequestBody TransactionDTO t) throws BudgetExceededException {
		ApiResponse<TransactionDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Transaction added", transactionService.addTransaction(userId, t));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
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
