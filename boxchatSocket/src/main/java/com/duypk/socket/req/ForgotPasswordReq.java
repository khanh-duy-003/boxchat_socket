package com.duypk.socket.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordReq {
	
	private String username;
	private String email;
	private String phone;

}