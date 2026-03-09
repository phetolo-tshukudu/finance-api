package com.phetolo.Financeapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.dto.AuthResponse;
import com.phetolo.Financeapi.dto.LoginRequest;
import com.phetolo.Financeapi.payload.ApiResponse;
import com.phetolo.Financeapi.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private AuthenticationManager manager;
	private JwtService jwtService;
	
	public AuthController(AuthenticationManager manager, JwtService jwtService) {
		this.manager = manager;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request){
		manager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		String token = jwtService.generateToken(request.getUsername());
		ApiResponse<AuthResponse> response = new ApiResponse<>(HttpStatus.ACCEPTED.value(), "Username and Password accepted", new AuthResponse(token));
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
}
