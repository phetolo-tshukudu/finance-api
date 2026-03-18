package com.phetolo.Financeapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.dto.AuthResponse;
import com.phetolo.Financeapi.dto.LoginRequest;
import com.phetolo.Financeapi.dto.RegisterRequest;
import com.phetolo.Financeapi.enums.Role;
import com.phetolo.Financeapi.exception.UserNotFoundException;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.repository.UserRepository;
import com.phetolo.Financeapi.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private AuthenticationManager manager;
	private JwtService jwtService;
	private UserRepository Urepo;
	
	public AuthController(AuthenticationManager manager, JwtService jwtService,UserRepository Urepo) {
		this.manager = manager;
		this.jwtService = jwtService;
		this.Urepo = Urepo;
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
		
		try {
		manager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		 org.springframework.security.core.userdetails.User userDetails = Urepo.findByEmail(request.getUsername())
                 .map(user -> new org.springframework.security.core.userdetails.User(
                         user.getEmail(),
                         user.getPassword(),
                         List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                 ))
                 .orElseThrow(() -> new UserNotFoundException("User not found"));

		String token = jwtService.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request){
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPass = passwordEncoder.encode(request.getPassword());
		
		User user = new User();
		user.setEmail(request.getUsername());
		user.setPassword(encodedPass);
		user.setRole(Role.USER);
		Urepo.save(user);
		return ResponseEntity.ok("User registered Successfully");
	}
}
