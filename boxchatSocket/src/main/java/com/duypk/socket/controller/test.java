package com.duypk.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duypk.socket.entity.PkDevAccountEntity;
import com.duypk.socket.service.testsql;

@RestController
public class test {
	
	@Autowired
	testsql stsql;
	
	@GetMapping("/getdata")
	public PkDevAccountEntity testtt() {
		
		return stsql.testsql();
	}

}
