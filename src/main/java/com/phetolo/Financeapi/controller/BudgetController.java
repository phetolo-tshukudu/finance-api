package com.phetolo.Financeapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.dto.BudgetDTO;
import com.phetolo.Financeapi.payload.ApiResponse;
import com.phetolo.Financeapi.service.BudgetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/{userId}/budgets")
public class BudgetController {
	private BudgetService budgetService;
	
	public BudgetController(BudgetService budgetService) {
		this.budgetService=budgetService;
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<BudgetDTO>>  createBudget(@PathVariable Long userId,@Valid @RequestBody BudgetDTO b) {
		BudgetDTO bu = budgetService.createBudget(userId, b);
		ApiResponse<BudgetDTO> response = new ApiResponse<BudgetDTO>(HttpStatus.CREATED.value(), "Budget created successfully", bu);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse< List<BudgetDTO>>> getBudgets(Long userId){
		
		ApiResponse<List<BudgetDTO>> response = new ApiResponse<List<BudgetDTO>>(HttpStatus.FOUND.value(), "Budgets found successfully", budgetService.getUserBudgets(userId));
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public BudgetDTO updateBudget(@PathVariable Long userId,@PathVariable Long id,@Valid @RequestBody BudgetDTO b) {
		return budgetService.updateBudget(userId, id, b);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBudget(@PathVariable Long userId,@PathVariable Long id) {
		budgetService.deleteBudget(userId, id);
	}
}
