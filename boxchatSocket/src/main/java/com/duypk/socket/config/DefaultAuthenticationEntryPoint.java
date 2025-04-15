package com.duypk.socket.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.duypk.socket.core.abstractres.RestErrorHandle;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private ObjectMapper objectMapper;
    private RestErrorHandle restErrorHandle;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(restErrorHandle.handleException(
                e, httpServletRequest, httpServletResponse, 0L)));
        httpServletResponse.getWriter().flush();
    }

    public DefaultAuthenticationEntryPoint(ObjectMapper objectMapper, RestErrorHandle restErrorHandle) {
        this.objectMapper = objectMapper;
        this.restErrorHandle = restErrorHandle;
    }

}
