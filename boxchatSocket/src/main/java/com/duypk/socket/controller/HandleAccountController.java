package com.duypk.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duypk.socket.core.abstractres.AbstractRest;
import com.duypk.socket.core.basereponse.BaseRes;
import com.duypk.socket.req.ForgotPasswordReq;
import com.duypk.socket.req.RegisterReq;
import com.duypk.socket.service.AccountLoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class HandleAccountController extends AbstractRest {
	
	@Autowired
	private AccountLoginService accountLoginService;
	
	
	@PostMapping("/auth/register")
    public BaseRes register(@RequestBody RegisterReq req, HttpServletRequest request, HttpServletResponse response) throws Exception{
        long start = System.currentTimeMillis();
        try {
            accountLoginService.register(req);
            return this.restSuccessHandle.handleSuccess("Success", start);
        } catch (Exception ex) {
            return this.restErrorHandle.handleException(ex, request, response, start);
        }
    }
    
    @PostMapping("/auth/forgot-pass")
    public BaseRes forgotPass(@RequestBody ForgotPasswordReq req, HttpServletRequest request, HttpServletResponse response) throws Exception{
        long start = System.currentTimeMillis();
        try {
            accountLoginService.forgotPass(req);
            return this.restSuccessHandle.handleSuccess("Success", start);
        } catch (Exception ex) {
            return this.restErrorHandle.handleException(ex, request, response, start);
        }
    }

}
