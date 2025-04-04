package com.duypk.socket.core.abstractres;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRest {
	
	/** The rest error handle. */
    @Autowired
    protected RestErrorHandle restErrorHandle;

    /** The rest success handle. */
    @Autowired
    protected RestSuccessHandle restSuccessHandle;

}
