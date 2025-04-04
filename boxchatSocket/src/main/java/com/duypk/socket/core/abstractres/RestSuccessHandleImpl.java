package com.duypk.socket.core.abstractres;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.duypk.socket.core.basereponse.BaseRes;

@Service
public class RestSuccessHandleImpl implements RestSuccessHandle {
	
	@Override
    public BaseRes handleSuccess(Object resObj, long start) {
        long took = System.currentTimeMillis() - start;
        return new BaseRes(HttpStatus.OK.value(), HttpStatus.OK.name(), "Successfully", resObj, took);
    }

}
