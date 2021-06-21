package com.pn.home.util;

import java.util.UUID;

import com.pn.home.common.AppExceptionHandler;
import com.pn.home.constants.ErrorConstants;

public class AppUtils {
	private static final String CLASS_NAME = AppUtils.class.getName();

	public static String generateGUUID() {
		UUID guuid = null;

		try {
			guuid = UUID.randomUUID();
		} catch (Exception e) {
			AppExceptionHandler.handleException(CLASS_NAME, ErrorConstants.UTL0001, ErrorConstants.UTL0001_MESSAGE, e);
			guuid = UUID.fromString("unable to generate GUUID");
		}

		return (guuid.toString());
	}
}
