package com.phetolo.Financeapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.phetolo.Financeapi.enums.TransactionStatus;
import com.phetolo.Financeapi.enums.TransactionType;

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
	@NotBlank(message="Category cannot be empty")
	private String Category;
	
	private String description;
	
	private LocalDate date;
	@NotNull(message="Transaction status needed")
	private TransactionStatus status;
	
	public TransactionDTO(Long id, BigDecimal amount, TransactionType type, String category, String description,
			LocalDate date, TransactionStatus status) {
		this.id = id;
		this.amount = amount;
		this.type = type;
		Category = category;
		this.description = description;
		this.date = date;
		this.status = status;
	}

	public TransactionDTO(BigDecimal amount, TransactionType type, String category, String description, LocalDate date,
			TransactionStatus status) {
		this.amount = amount;
		this.type = type;
		Category = category;
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

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
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
