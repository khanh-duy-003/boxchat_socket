package com.duypk.socket.core.abstractres;

import com.duypk.socket.core.basereponse.BaseRes;

public interface RestSuccessHandle {
	
	BaseRes handleSuccess(Object resObj, long start);

}
