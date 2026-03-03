package com.phetolo.Financeapi.model;

import java.math.BigDecimal;
import java.time.YearMonth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Budget")
public class Budget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String Category;
	private BigDecimal monthlyLimit;
	private YearMonth month;
	@ManyToOne
	@JoinColumn(name ="user_id")
	private User user;
	
	public Budget(Long id, String category, BigDecimal monthlyLimit, YearMonth month, User user) {
		this.id = id;
		Category = category;
		this.monthlyLimit = monthlyLimit;
		this.month = month;
		this.user = user;
	}
	public Budget(String category, BigDecimal monthlyLimit, YearMonth month, User user) {
		Category = category;
		this.monthlyLimit = monthlyLimit;
		this.month = month;
		this.user = user;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
