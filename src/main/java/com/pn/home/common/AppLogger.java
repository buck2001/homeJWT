package com.pn.home.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pn.home.constants.AppConstants;

/**
 * logging helper for the service
 * 
 * @author pnaylor
 **/
public class AppLogger {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppLogger.class);
	public final static String ENTRY = "ENTRY: ";
	public final static String EXIT = "EXIT: ";
	public final static String WARNING = "WARNING: ";

	public static void log(String className, String methodName, String id, Object input, String exitOrEntry) {
		ObjectMapper objectMapper = new ObjectMapper();
		String data = null;

		if (input != null) {
			try {
				data = objectMapper.writeValueAsString(input);
			} catch (JsonProcessingException e1) {
				if (input instanceof String) {
					data = (String) input;
				}
			}

			data = AppConstants.NEW_LINE + AppConstants.TAB + data;
		}

		LOGGER.info("{} {} {} {} {} {}", exitOrEntry, className, methodName, id, input, data);
	}
}
