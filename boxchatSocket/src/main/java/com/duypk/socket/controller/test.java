package com.duypk.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duypk.socket.service.testsql;

@RestController
public class test {
	
	@Autowired
	private testsql stsql;
	
	@GetMapping("/home")
	public ResponseEntity<String> testtt() {
		
		return new ResponseEntity<>("abcv", HttpStatus.OK);
	}

}
