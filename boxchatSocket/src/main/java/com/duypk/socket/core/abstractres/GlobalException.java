package com.duypk.socket.core.abstractres;

import com.duypk.socket.core.basereponse.ExceptionCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2392926148247590423L;

    /** The exception code. */
    protected ExceptionCode exceptionCode;
}