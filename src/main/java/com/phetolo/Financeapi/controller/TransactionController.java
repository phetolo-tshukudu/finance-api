package com.phetolo.Financeapi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.dto.TransactionDTO;
import com.phetolo.Financeapi.exception.BudgetExceededException;
import com.phetolo.Financeapi.exception.TransactionNotFoundException;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.payload.ApiResponse;
import com.phetolo.Financeapi.repository.UserRepository;
import com.phetolo.Financeapi.service.TransactionServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private TransactionServices transactionService;
	private UserRepository userRepo;
	
	public TransactionController(TransactionServices transactionService,UserRepository userRepo) {
		this.transactionService = transactionService;
		this.userRepo = userRepo;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<TransactionDTO>>> getAll(@AuthenticationPrincipal UserDetails userdetails) throws TransactionNotFoundException{
		User user = userRepo.getByEmail(userdetails.getUsername());
		ApiResponse<List<TransactionDTO>> response = new ApiResponse<>(HttpStatus.FOUND.value(), "Transactions found", transactionService.getUserTransactions(user.getId()));
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<TransactionDTO>> addTransactions(@AuthenticationPrincipal UserDetails userdetails,@Valid @RequestBody TransactionDTO t) throws BudgetExceededException {
		User user = userRepo.getByEmail(userdetails.getUsername());
		TransactionDTO dto = transactionService.addTransaction(user.getId(), t);
		if(dto.getId()==null) {
			ApiResponse<TransactionDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Transaction could not be added, expenses exceed income.", transactionService.addTransaction(user.getId(), t));
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}
		ApiResponse<TransactionDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Transaction added", transactionService.addTransaction(user.getId(), t));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/ai")
	public ResponseEntity<ApiResponse<TransactionDTO>> addTransactionsWithAi(@AuthenticationPrincipal UserDetails userdetails,@Valid @RequestBody TransactionDTO t){
		User user = userRepo.getByEmail(userdetails.getUsername());
		TransactionDTO dto = transactionService.addTransactionWithAi(user.getId(), t);
		if(dto.getId()==null) {
			ApiResponse<TransactionDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Transaction could not be added, expenses exceed income.", transactionService.addTransaction(user.getId(), t));
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}
		ApiResponse<TransactionDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Transaction added", transactionService.addTransaction(user.getId(), t));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/balance")
	public Double getBalannce(@AuthenticationPrincipal UserDetails userdetails) {
		User user = userRepo.getByEmail(userdetails.getUsername());
		return transactionService.calculateBalance(user.getId());
	}
	
	@DeleteMapping("/{id}")
	public void deleteTransaction(@AuthenticationPrincipal UserDetails userdetails,@PathVariable Long id) {
		User user = userRepo.getByEmail(userdetails.getUsername());
		 transactionService.deleteTransaction(user.getId(), id);
	}
}
