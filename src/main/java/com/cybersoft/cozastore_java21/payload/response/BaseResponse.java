package com.cybersoft.cozastore_java21.payload.response;

public class BaseResponse {
	private int statuscode;
	private String message;
	private Object data;

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public BaseResponse(int statuscode, String message, Object data) {
		super();
		this.statuscode = statuscode;
		this.message = message;
		this.data = data;
	}

	public BaseResponse() {
		super();
	}

}
