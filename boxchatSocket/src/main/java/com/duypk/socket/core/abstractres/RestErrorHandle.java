package com.duypk.socket.core.abstractres;

import org.springframework.validation.BindingResult;

import com.duypk.socket.core.basereponse.BaseRes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

public interface RestErrorHandle {
	
	BaseRes handleBindingResult(BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse httpServletResponse, long start);
	
	BaseRes handleException(Exception ex, HttpServletRequest request, HttpServletResponse httpServletResponse,
            long start);
	
	BaseRes handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request,
            HttpServletResponse httpServletResponse, long start);

}
