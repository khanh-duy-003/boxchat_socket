package com.duypk.socket.core.abstractres;

import com.duypk.socket.core.basereponse.ExceptionCode;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class DetailException extends GlobalException {

    private boolean isTranslate = true; // default isTranslate use i18n

    private String specificMsg;

    private String exceptionErrorCode;

    private Object[] paramater;

    public DetailException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public DetailException(ExceptionCode exceptionCode, boolean isTranslate) {
        super(exceptionCode);
        this.isTranslate = isTranslate;
    }

    public DetailException(ExceptionCode exceptionCode, String specificMsg) {
        super(exceptionCode);
        this.specificMsg = specificMsg;
    }

    public DetailException(ExceptionCode exceptionCode, String specificMsg, boolean isTranslate) {
        super(exceptionCode);
        this.isTranslate = isTranslate;
        this.specificMsg = specificMsg;
    }

    public DetailException(String exceptionErrorCode, Object[] paramater, boolean isTranslate) {
        super(new ExceptionCode(exceptionErrorCode));
        this.exceptionErrorCode = exceptionErrorCode;
        this.isTranslate = isTranslate;
        this.paramater = paramater;
    }

    public DetailException(String exceptionErrorCode, Object[] paramater) {
        super(new ExceptionCode(exceptionErrorCode));
        this.exceptionErrorCode = exceptionErrorCode;
        this.paramater = paramater;
    }

    public DetailException(String exceptionErrorCode, boolean isTranslate) {
        super(new ExceptionCode(exceptionErrorCode));
        this.exceptionErrorCode = exceptionErrorCode;
        this.isTranslate = isTranslate;
    }

    public DetailException(String exceptionErrorCode) {
        super(new ExceptionCode(exceptionErrorCode));
        this.exceptionErrorCode = exceptionErrorCode;
    }

}