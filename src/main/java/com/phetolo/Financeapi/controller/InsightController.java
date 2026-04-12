package com.phetolo.Financeapi.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.Financeapi.dto.InsightResponsedto;
import com.phetolo.Financeapi.exception.UserNotFoundException;
import com.phetolo.Financeapi.model.User;
import com.phetolo.Financeapi.repository.UserRepository;
import com.phetolo.Financeapi.service.InsightService;

@RestController
@RequestMapping("/insights")
public class InsightController {
	
	private InsightService inService ;
	private UserRepository Urepo;

	
	
	public InsightController(InsightService inService, UserRepository urepo) {
		this.inService = inService;
		Urepo = urepo;
	}

	@GetMapping
	public ResponseEntity<InsightResponsedto> getInsghts(@AuthenticationPrincipal UserDetails userdetails){
		Optional<User> user = Urepo.findByEmail(userdetails.getUsername());
		if(user.isEmpty()) {
			throw new UserNotFoundException("Could not find the user with username: "+userdetails.getUsername());
		}
		return ResponseEntity.ok(inService.getInsights(user.get().getId()));
	}
	
}
