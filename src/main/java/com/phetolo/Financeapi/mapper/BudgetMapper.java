package com.phetolo.Financeapi.mapper;

import com.phetolo.Financeapi.dto.BudgetDTO;
import com.phetolo.Financeapi.model.Budget;

public class BudgetMapper {
	
	public static BudgetDTO mapToDto(Budget budget) {
		return new BudgetDTO(budget.getId(),budget.getCategory(),budget.getMonthlyLimit(),budget.getMonth());
	}
	
	public static Budget mapToEntity(BudgetDTO budgetdto) {
		return new Budget(budgetdto.getId(),budgetdto.getCategory(),budgetdto.getMonthlyLimit(),budgetdto.getMonth(),null);
	}
}
