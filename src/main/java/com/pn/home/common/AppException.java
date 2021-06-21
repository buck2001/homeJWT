package com.pn.home.common;

/**
 * generic exception for the service
 * 
 * @author pnaylor4
 **/
public class AppException extends Exception {
	private static final long serialVersionUID = 8247139058181612741L;
	private String errorCode;

	public AppException() {
	}

	public AppException(final String message) {
		super(message);
	}

	public AppException(final Throwable cause) {
		super(cause);
	}

	public AppException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public AppException(final String errorCode, final String message, final Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
