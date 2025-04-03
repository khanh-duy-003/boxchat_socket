package com.duypk.socket.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duypk.socket.req.LoginReq;
import com.duypk.socket.service.AccountLoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
//	@GetMapping("/login")
//	public ResponseEntity<String> testtt() {
//		
//		return new ResponseEntity<>("login", HttpStatus.OK);
//	}
	
	@Autowired
	private AccountLoginService accountLoginService;
    
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq request) throws Exception{
        var result = accountLoginService.checkExitAccount(request);
        return ResponseEntity.ok(Map.of(result, result));
    }

}
