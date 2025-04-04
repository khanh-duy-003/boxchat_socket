package com.duypk.socket.core.basereponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseRes {
	
	/** status : 200/400/500 - Mandatory */
	private int status;

	/** message : Success/Bad request/Internal error - Mandatory */
	private String message;
	/**
	 * title : nội dung message trả về ("Success" : status 200, "Validate error",
	 * "Business exception" : 400, "System Exception" : status 500 ) - Mandatory
	 */
	private String title;

	/** time : thời điểm thực thi xong - Mandatory */
	private Date time;

	/** took : khoảng thời gian đã thực thi - Mandatory */
	private long took;

	/** errors : detail error - optional */
	private List<ErrorDetailRes> errors;

	/** data : data return */
	private Object data;
	
	public BaseRes(int status, String message, String title, long took) {
		this.status = status;
		this.message = message;
		this.title = title;
		this.took = took;
		this.time = new Date();
	}

	public BaseRes(int status, String message, String title, Object data, long took) {
		this.status = status;
		this.message = message;
		this.title = title;
		this.data = data;
		this.took = took;
		this.time = new Date();
	}

	public BaseRes(BaseException baseException, long took) {
		this.status = baseException.getStatus();
		this.message = baseException.getMessage();
		this.title = baseException.getTitle();
		this.took = took;
		this.time = new Date();
	}

	public void addError(ErrorDetailRes errorDetailRes) {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
		this.errors.add(errorDetailRes);
	}

}
