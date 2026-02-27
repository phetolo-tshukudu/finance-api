package com.phetolo.Financeapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.model.Budget;
import com.phetolo.Financeapi.service.BudgetService;

@RestController
@RequestMapping("/users/{userId}/budgets")
public class BudgetController {
	private BudgetService budgetService;
	
	public BudgetController(BudgetService budgetService) {
		this.budgetService=budgetService;
	}
	
	@PostMapping
	public Budget createBudget(@PathVariable Long userId,@RequestBody Budget b) {
		return budgetService.createBudget(userId, b);
	}
	
	@GetMapping
	public List<Budget> getBudgets(Long userId){
		return budgetService.getUserBudgets(userId);
	}
	
	@PutMapping("/{id}")
	public Budget updateBudget(@PathVariable Long userId,@PathVariable Long id, @RequestBody Budget b) {
		return budgetService.updateBudget(userId, id, b);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBudget(@PathVariable Long userId,@PathVariable Long id) {
		budgetService.deleteBudget(userId, id);
	}
}
