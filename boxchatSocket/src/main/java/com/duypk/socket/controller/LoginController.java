package com.duypk.socket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@GetMapping("/login")
	public ResponseEntity<String> testtt() {
		
		return new ResponseEntity<>("login", HttpStatus.OK);
	}

}
