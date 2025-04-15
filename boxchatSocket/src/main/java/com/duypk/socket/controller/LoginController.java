package com.duypk.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duypk.socket.core.abstractres.AbstractRest;
import com.duypk.socket.core.basereponse.BaseRes;
import com.duypk.socket.req.LoginReq;
import com.duypk.socket.req.RegisterReq;
import com.duypk.socket.service.AccountLoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class LoginController extends AbstractRest {
	
	@Autowired
	private AccountLoginService accountLoginService;
	
	@PostMapping("/login")
    public BaseRes login(@RequestBody LoginReq req, HttpServletRequest request, HttpServletResponse response) throws Exception{
		long start = System.currentTimeMillis();
		try {
	        var result = accountLoginService.checkExitAccount(req);
	        return this.restSuccessHandle.handleSuccess(result, start);
		} catch (Exception ex) {
            return this.restErrorHandle.handleException(ex, request, response, start);
        }
    }
	
	@PostMapping("/register")
    public BaseRes register(@RequestBody RegisterReq req, HttpServletRequest request, HttpServletResponse response) throws Exception{
        long start = System.currentTimeMillis();
        try {
            accountLoginService.register(req);
            return this.restSuccessHandle.handleSuccess("Success", start);
        } catch (Exception ex) {
            return this.restErrorHandle.handleException(ex, request, response, start);
        }
    }
    
    @PostMapping("/forgot-pass")
    public BaseRes forgotPass(@RequestBody LoginReq req, HttpServletRequest request, HttpServletResponse response) throws Exception{
        long start = System.currentTimeMillis();
        try {
            var result = accountLoginService.checkExitAccount(req);
            return this.restSuccessHandle.handleSuccess(result, start);
        } catch (Exception ex) {
            return this.restErrorHandle.handleException(ex, request, response, start);
        }
    }

}
