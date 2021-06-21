package com.pn.home.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);
	private static final String ERROR_START_MESSAGE = "Error in class ";

	public AppExceptionHandler() {
	}

	public static AppException handleException(String className, String message, Throwable t) {
		AppException aobException = null;

		// check if already caught and logged
		if (t instanceof AppException) {
			aobException = (AppException) t;
		} else { // if not caught and logged then log it
			LOGGER.error(ERROR_START_MESSAGE + className + " - " + message, t);
			aobException = new AppException(message, t);
		}

		return (aobException);
	}

	public static AppException handleException(String className, String errorCode, String message, Throwable t) {
		AppException aobException = null;

		// check if already caught and logged
		if (t instanceof AppException) {
			aobException = (AppException) t;
		} else { // if not caught and logged then log it
			LOGGER.error(ERROR_START_MESSAGE + className + " - " + message, t);
			aobException = new AppException(errorCode, message, t);
		}

		return (aobException);
	}
}
