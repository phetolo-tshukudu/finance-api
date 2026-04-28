package com.phetolo.Financeapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.phetolo.Financeapi.enums.TransactionStatus;
import com.phetolo.Financeapi.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Transaction",indexes= {@Index(name = "index_userId_date",columnList = "user_id,date")})//indexes user_id and date
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal amount;
	private TransactionType type;
	private Category category;
	private String description;
	private LocalDate date;
	private TransactionStatus status;
	@ManyToOne
	@JoinColumn(name ="user_id")
	private User user;
	
	public Transaction(Long id, BigDecimal amount, TransactionType type, Category category, String description,
			LocalDate date, TransactionStatus status, User user) {
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.category = category;
		this.description = description;
		this.date = date;
		this.status = status;
		this.user = user;
	}

	public Transaction(BigDecimal amount, TransactionType type, Category category, String description, LocalDate date,
			TransactionStatus status, User user) {
		this.amount = amount;
		this.type = type;
		this.category = category;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
