package com.phetolo.Financeapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.dto.UpdatedUserDto;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.payload.ApiResponse;

import com.phetolo.Financeapi.service.UserServices;



@RestController
@RequestMapping("/admin/users")
@EnableMethodSecurity
public class UserController {
	private UserServices userServices;
	
	public UserController(UserServices userServices) {
		this.userServices = userServices;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<ApiResponse<List<User>>>  getAllUsers(){
		ApiResponse<List<User>> response = new ApiResponse<>(HttpStatus.FOUND.value(), "Users found", userServices.getAll());
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/id/{userId}")
	public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long userId) {
		ApiResponse<User> response = new ApiResponse<>(HttpStatus.FOUND.value(), "User found", userServices.findById(userId));
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/email/{email}")
	public ResponseEntity<ApiResponse<User>> getUserByEmail(@PathVariable String email) {
		ApiResponse<User> response = new ApiResponse<>(HttpStatus.FOUND.value(), "User found", userServices.findUserByEmail(email));
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/summary/{userId}")
	public ResponseEntity<ApiResponse<String>> getSummary(@PathVariable Long userId) {
		
		ApiResponse<String> response = new ApiResponse<>(HttpStatus.FOUND.value(), "User found", userServices.findById(userId).toString());
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		ApiResponse<User> response = new ApiResponse<>(HttpStatus.CREATED.value(), "User created", userServices.registerUser(user));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/modify/{userId}")
	public ResponseEntity<ApiResponse<User>> editRole(@PathVariable Long userId,@RequestBody UpdatedUserDto update){
		User updated = userServices.updateUserByAdmin(userId, update);
		ApiResponse<User> response = new ApiResponse<User>(HttpStatus.ACCEPTED.value(), "User edited, with new status", updated);
		return ResponseEntity.ok(response);
	}
}
