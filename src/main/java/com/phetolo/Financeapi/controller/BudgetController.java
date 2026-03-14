package com.phetolo.Financeapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.dto.BudgetDTO;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.payload.ApiResponse;
import com.phetolo.Financeapi.repository.BudgetRepository;
import com.phetolo.Financeapi.repository.UserRepository;
import com.phetolo.Financeapi.service.BudgetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/budgets")
public class BudgetController {
	private BudgetService budgetService;
	private UserRepository userRepo;
	
	public BudgetController(BudgetService budgetService,UserRepository userRepo) {
		this.budgetService=budgetService;
		this.userRepo = userRepo;
		
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<BudgetDTO>>  createBudget(@AuthenticationPrincipal UserDetails userdetails,@Valid @RequestBody BudgetDTO b) {
		User user = userRepo.getByEmail(userdetails.getUsername());
		BudgetDTO bu = budgetService.createBudget(user.getId(), b);
		ApiResponse<BudgetDTO> response = new ApiResponse<BudgetDTO>(HttpStatus.CREATED.value(), "Budget created successfully", bu);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse< List<BudgetDTO>>> getBudgets(@AuthenticationPrincipal UserDetails userdetails){
		User user = userRepo.getByEmail(userdetails.getUsername());
		ApiResponse<List<BudgetDTO>> response = new ApiResponse<List<BudgetDTO>>(HttpStatus.FOUND.value(), "Budgets found successfully", budgetService.getUserBudgets(user.getId()));
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public BudgetDTO updateBudget(@AuthenticationPrincipal UserDetails userdetail,@PathVariable Long id,@Valid @RequestBody BudgetDTO b) {
		User user = userRepo.getByEmail(userdetail.getUsername());
		return budgetService.updateBudget(user.getId(), id, b);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBudget(@AuthenticationPrincipal UserDetails userdetail,@PathVariable Long id) {
		User user = userRepo.getByEmail(userdetail.getUsername());
		budgetService.deleteBudget(user.getId(), id);
	}
}
