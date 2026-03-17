package com.phetolo.Financeapi.dto;

import com.phetolo.Financeapi.enums.Role;

public class UpdatedUserDto {
	private Role role;
	private boolean active;
	
	public UpdatedUserDto(Role role, boolean active) {
		this.role = role;
		this.active = active;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
