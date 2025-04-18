package com.duypk.socket.core.basereponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception {

	private final int status;
	private final String message;
	private final String title;
	private final ExceptionCode exceptionCode;
	private final Object[] args;
	private final String defaultMessage;

	public BaseException(int status, String message, String title, String exceptionCode, Object[] args,
			String defaultMessage) {
		this.status = status;
		this.message = message;
		this.title = title;
		this.exceptionCode = new ExceptionCode(exceptionCode);
		this.args = args;
		this.defaultMessage = defaultMessage;
	}

}
