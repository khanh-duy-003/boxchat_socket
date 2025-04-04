package com.duypk.socket.core.basereponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetailRes {
	
	/** code : ERROR CODE defined before */
	private String code;
	/** message : Message Error summary */
	private String message;
	/** objectName : object data error, this value is optional */
	private String objectName;
	/** field : field in objectName, this value is optional */
	private String field;
	/** rejectValue : value rejected */
	private Object rejectValue;

}
