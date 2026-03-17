package com.phetolo.Financeapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phetolo.Financeapi.dto.BudgetDTO;
import com.phetolo.Financeapi.mapper.BudgetMapper;
import com.phetolo.Financeapi.model.Budget;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.repository.BudgetRepository;
import com.phetolo.Financeapi.repository.UserRepository;
@Service
public class BudgetService {
	private BudgetRepository Brepo;
	private UserRepository Urepo;
	List<Budget> budgets;
	
	public BudgetService(BudgetRepository Brepo,UserRepository Urepo) {
		this.Brepo = Brepo;
		this.Urepo = Urepo;
	}
	
	public BudgetDTO createBudget(Long userId,BudgetDTO b) {
		Optional<User> user = Urepo.findById(userId);
		
		Budget existing = Brepo.findByUserIdAndCategoryAndMonth(userId, b.getCategory(), b.getMonth());
		if(existing!= null) {
			throw new RuntimeException("The Budget already exists");
		}
		Budget budget = BudgetMapper.mapToEntity(b);
		budget.setUser(user.get());
		
		return BudgetMapper.mapToDto(Brepo.save(budget));
	}
	
	public List<BudgetDTO> getUserBudgets(Long userId){
		budgets = Brepo.findByUserId(userId);
		return budgets.stream().map(BudgetMapper::mapToDto).toList();
	}
	
	public BudgetDTO updateBudget(Long userId, Long budgetId, BudgetDTO updatedBudget) {
		Optional<Budget> b = Brepo.findById(budgetId);
		
		if(b.get().getUser().getId()!=userId) {
			throw new RuntimeException("Unauthorized");
		}
		b.get().setMonthlyLimit(updatedBudget.getMonthlyLimit());
		return BudgetMapper.mapToDto(Brepo.save(b.get()));
	}
	
	public void deleteBudget(Long userId, Long budgetId) {
		Optional<Budget> b = Brepo.findById(budgetId);
		if(b.get().getUser().getId()!=userId) {
			throw new RuntimeException("Unauthorized");
		}
		Brepo.deleteById(budgetId);
	}
	
	
}
