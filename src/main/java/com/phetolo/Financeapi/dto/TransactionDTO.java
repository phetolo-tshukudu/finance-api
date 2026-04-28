package com.phetolo.Financeapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.phetolo.Financeapi.enums.TransactionStatus;
import com.phetolo.Financeapi.enums.TransactionType;
import com.phetolo.Financeapi.model.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransactionDTO {
	private Long id;
	@NotNull(message="Amount required")
	@Positive(message="Amount cannot be negative")
	private BigDecimal amount;
	@NotNull(message="Transaction Type needed")
	private TransactionType type;
	
	private Category category;
	@NotBlank(message="Description cannot be empty")
	private String description;
	
	private LocalDate date;
	@NotNull(message="Transaction status needed")
	private TransactionStatus status;
	
	public TransactionDTO(Long id, BigDecimal amount, TransactionType type, Category category, String description,
			LocalDate date, TransactionStatus status) {
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.category = category;
		this.description = description;
		this.date = date;
		this.status = status;
	}

	public TransactionDTO(BigDecimal amount, TransactionType type, Category category, String description, LocalDate date,
			TransactionStatus status) {
		this.amount = amount;
		this.type = type;
		this.category = category;
		this.description = description;
		this.date = date;
		this.status = status;
	}

	public TransactionDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	
	
}
