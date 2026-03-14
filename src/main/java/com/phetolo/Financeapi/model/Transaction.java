package com.phetolo.Financeapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.phetolo.Financeapi.enums.TransactionStatus;
import com.phetolo.Financeapi.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal amount;
	private TransactionType type;
	private String Category;
	private String description;
	private LocalDate date;
	private TransactionStatus status;
	@ManyToOne
	@JoinColumn(name ="user_id")
	private User user;
	
	public Transaction(Long id, BigDecimal amount, TransactionType type, String category, String description,
			LocalDate date, TransactionStatus status, User user) {
		this.id = id;
		this.amount = amount;
		this.type = type;
		Category = category;
		this.description = description;
		this.date = date;
		this.status = status;
		this.user = user;
	}

	public Transaction(BigDecimal amount, TransactionType type, String category, String description, LocalDate date,
			TransactionStatus status, User user) {
		this.amount = amount;
		this.type = type;
		Category = category;
		this.description = description;
		this.date = date;
		this.status = status;
		this.user = user;
		
	}
	

	

	public Transaction() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
