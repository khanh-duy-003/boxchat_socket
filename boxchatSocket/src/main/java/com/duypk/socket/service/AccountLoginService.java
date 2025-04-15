package com.duypk.socket.service;

import com.duypk.socket.req.LoginReq;
import com.duypk.socket.req.RegisterReq;
import com.duypk.socket.res.LoginRes;

public interface AccountLoginService {
	
	public LoginRes checkExitAccount(LoginReq req) throws Exception;
	
	public void register(RegisterReq req) throws Exception;

}
