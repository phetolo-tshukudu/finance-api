package com.phetolo.Financeapi.model;
import java.time.LocalDateTime;

import com.phetolo.Financeapi.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private  String name;
	@Column(unique = true)
	private String email;
	
	private String password;
	private LocalDateTime createdAt;
	private boolean active;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	
	public User(long id, String name, String email, String password, LocalDateTime createdAt, boolean active) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.active = active;
	}

	
	public User(String name, String email, String password, LocalDateTime createdAt, boolean active) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.active = active;
	}


	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", createdAt="
				+ createdAt + ", active=" + active + ", role=" + role + "]";
	}
	
	
	
}
