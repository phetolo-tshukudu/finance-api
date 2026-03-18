package com.phetolo.Financeapi.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phetolo.Financeapi.dto.UpdatedUserDto;
import com.phetolo.Financeapi.enums.Role;
import com.phetolo.Financeapi.exception.IllegalEntityException;
import com.phetolo.Financeapi.exception.UserNotFoundException;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.repository.UserRepository;

@Service
public class UserServices {
	private List<User> registeredUsers;
	private UserRepository Urepo;
	
	//Injecting dependencies
	public UserServices(UserRepository Urepo) {
		this.Urepo = Urepo;
	}
	
	public User registerUser(User user) {
		user.setRole(Role.USER);
		user.setCreatedAt(LocalDateTime.now());
		return Urepo.save(user);
	}
	
	public void deactivateUser(User user) throws IllegalEntityException {
		if(!Urepo.existsByEmail(user.getEmail())) {
			throw new UserNotFoundException("Cannot Delete. "+user+" does not exists");
		}
		Urepo.delete(user);
	}
	
	public User findUserByEmail(String email) {
		if(!Urepo.existsByEmail(email)) {
			throw new UserNotFoundException("User not found for email: "+ email);
		}
		return Urepo.findByEmail(email).get();
	}
	
	public User findById(Long id) {
		if(!Urepo.existsById(id)) {
			throw new UserNotFoundException("User not found for ID: "+ id);
		}
		return Urepo.findById(id).get();
	}
	
	public String getUserSummary(User user) {
		return user.toString();
		
	}
	
	public List<User> getAll(){
		registeredUsers = Urepo.findAll();
		return registeredUsers;
	}
	
	public User updateUserByAdmin(Long userId,UpdatedUserDto updated) {
		if(!Urepo.existsById(userId)) {
			throw new UserNotFoundException("User not found for ID: "+ userId);
		}
		
		Optional<User> optionalUser = Urepo.findById(userId);
		User toUpdate = optionalUser.get();
		if(updated.getRole()!=null) {
			toUpdate.setRole(updated.getRole());
			toUpdate.setActive(updated.isActive());
		}
		
		return Urepo.save(toUpdate);
	}
	
}
