package com.duypk.socket.core.basereponse;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionCode implements Serializable {
	
	/** serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    public static final String UNDERLINED = "_";

    /** The text. */
    private String text;

    /** The value. */
    private int value;

    /**
     * Instantiates a new exception code.
     *
     * @param exceptionErrorCode
     *            the exception error code
     */
    public ExceptionCode(String exceptionErrorCode) {
        int indexUnderlinedFirst = StringUtil.indexOf(exceptionErrorCode, UNDERLINED);
        this.value = Integer.parseInt(StringUtil.substring(exceptionErrorCode, 0, indexUnderlinedFirst));
        this.text = StringUtil.substring(exceptionErrorCode, indexUnderlinedFirst + 1,
                StringUtil.length(exceptionErrorCode));
    }

}
