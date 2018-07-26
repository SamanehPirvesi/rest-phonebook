package com.rest.phonebook.exception;

public class ErrorResponse {
	private String errMasg;
	private String path;
	private int status;
	private String timestamp;
	
	
	public ErrorResponse() {
	}

	public ErrorResponse(String errMasg, String path, int status, String timestamp) {
		this.errMasg = errMasg;
		this.path = path;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getErrMasg() {
		return errMasg;
	}

	public void setErrMasg(String errMasg) {
		this.errMasg = errMasg;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}