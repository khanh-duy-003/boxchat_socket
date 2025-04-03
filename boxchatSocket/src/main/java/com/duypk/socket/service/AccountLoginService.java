package com.duypk.socket.service;

import com.duypk.socket.req.LoginReq;
import com.duypk.socket.res.LoginRes;

public interface AccountLoginService {
	
	public LoginRes checkExitAccount(LoginReq req) throws Exception;

}
