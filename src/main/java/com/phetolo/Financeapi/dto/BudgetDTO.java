package com.phetolo.Financeapi.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;





public class BudgetDTO {
	
private Long id;
	@NotBlank(message="Category needed")
	private String Category;
	@NotNull(message="Amount cannot be null")
	@Positive(message="Amount must be negative")
	private BigDecimal monthlyLimit;
	private YearMonth month;
	
	

	public BudgetDTO(Long id, String category, BigDecimal monthlyLimit, YearMonth month) {
		this.id = id;
		Category = category;
		this.monthlyLimit = monthlyLimit;
		this.month = month;
		
	}

	public BudgetDTO(String category, BigDecimal monthlyLimit, YearMonth month) {
		Category = category;
		this.monthlyLimit = monthlyLimit;
		this.month = month;
		
	}

	public BudgetDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public BigDecimal getMonthlyLimit() {
		return monthlyLimit;
	}

	public void setMonthlyLimit(BigDecimal monthlyLimit) {
		this.monthlyLimit = monthlyLimit;
	}

	public YearMonth getMonth() {
		return month;
	}

	public void setMonth(YearMonth month) {
		this.month = month;
	}

	
	
	
}
