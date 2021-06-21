package com.pn.home.errorHandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
public class AppExceptionHandler implements ErrorHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);
	private static final String ERROR_START_MESSAGE = "Error in class ";

	public AppExceptionHandler() {
	}

	@Override
	public void handleError(Throwable t) {
		LOGGER.error(ERROR_START_MESSAGE + " error  in listener ", t);
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
			LOGGER.error(errorCode + " - " + ERROR_START_MESSAGE + className + " - " + message, t);
			aobException = new AppException(errorCode, message, t);
		}

		return (aobException);
	}
}
