package com.duypk.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duypk.socket.core.abstractres.AbstractRest;
import com.duypk.socket.core.basereponse.BaseRes;
import com.duypk.socket.service.TestDBMongoSV;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class test extends AbstractRest {
	
	@Autowired
	private TestDBMongoSV stsql;
	
	@GetMapping("/home")
	public BaseRes testtt(HttpServletRequest request, HttpServletResponse response) {		
		long start = System.currentTimeMillis();
		try {
	        stsql.testDBMongo();
	        return this.restSuccessHandle.handleSuccess("Success", start);
		} catch (Exception ex) {
            return this.restErrorHandle.handleException(ex, request, response, start);
        }
	}

}
