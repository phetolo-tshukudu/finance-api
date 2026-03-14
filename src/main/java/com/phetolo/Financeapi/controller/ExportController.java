package com.phetolo.Financeapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import com.phetolo.Financeapi.service.ExportService;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.payload.ApiResponse;
import com.phetolo.Financeapi.repository.UserRepository;

@RestController
@RequestMapping("/transactions/export")
public class ExportController {
	private ExportService export;
	private UserRepository userRepo;
	
	
	public ExportController(ExportService export, UserRepository userRepo) {
		this.export = export;
		this.userRepo = userRepo;
	}

	@GetMapping("/csv")
	public ResponseEntity<ApiResponse<byte[]>> getCsv(@AuthenticationPrincipal UserDetails userdetails){
		
		User user = userRepo.getByEmail(userdetails.getUsername());
		String csv = export.exportTransactionsToCsv(user.getId());
		byte[] bytes = csv.getBytes();
		ApiResponse<byte[]> response = new ApiResponse<byte[]>(HttpStatus.CREATED.value(), "Csv file has been created", bytes);
		ResponseEntity<ApiResponse<byte[]>> entity = new ResponseEntity<>(response,HttpStatus.CREATED);
		return entity;
	}
}
