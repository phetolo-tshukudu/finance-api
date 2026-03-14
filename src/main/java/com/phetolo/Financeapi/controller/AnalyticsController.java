package com.phetolo.Financeapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import com.phetolo.Financeapi.service.AnalyticsService;
import com.phetolo.Financeapi.payload.ApiResponse;
import com.phetolo.Financeapi.repository.UserRepository;
import com.phetolo.Financeapi.dto.StatisticDTO;
import com.phetolo.Financeapi.model.User;
@RestController
@RequestMapping("/transactions/analytics")
public class AnalyticsController {

	private AnalyticsService analysis;
	private UserRepository userRepo;
	
	public AnalyticsController(AnalyticsService analysis,UserRepository userRepo) {
		this.analysis=analysis;
		this.userRepo = userRepo;
	}
	
	@GetMapping("/statistics")
	public ResponseEntity<ApiResponse<StatisticDTO>> getStatistics(@AuthenticationPrincipal UserDetails userdetails){
		User user = userRepo.getByEmail(userdetails.getUsername());
		StatisticDTO stats = analysis.computeStatistics(user.getId());
		ApiResponse<StatisticDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Transaction statistics created!", stats);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
