package com.phetolo.Financeapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.service.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserServices userServices;
	
	public UserController(UserServices userServices) {
		this.userServices = userServices;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userServices.getAll();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long userId) {
		return userServices.findById(userId);
	}
	
	@GetMapping("/{email}")
	public User getUserByEmail(@PathVariable String email) {
		return userServices.findUserByEmail(email);
	}
	
	@GetMapping("/summary")
	public String getSummary(@PathVariable Long userId) {
		return userServices.getUserSummary(userServices.findById(userId));
	}
	
	@PostMapping
    public User createUser(@RequestBody User user) {
        return userServices.registerUser(user);
    }
}
