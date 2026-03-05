package com.phetolo.Financeapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.phetolo.Financeapi.service.AnalyticsService;
import com.phetolo.Financeapi.payload.ApiResponse;
import com.phetolo.Financeapi.dto.StatisticDTO;
@RestController
@RequestMapping("users/{userId}/transactions/analytics")
public class AnalyticsController {

	private AnalyticsService analysis;
	
	public AnalyticsController(AnalyticsService analysis) {
		this.analysis=analysis;
	}
	
	@GetMapping("/statistics")
	public ResponseEntity<ApiResponse<StatisticDTO>> getStatistics(@PathVariable Long userId){
		StatisticDTO stats = analysis.computeStatistics(userId);
		ApiResponse<StatisticDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Transaction statistics created!", stats);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
