package com.pn.home.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import com.pn.home.constants.AppConstants;
import com.pn.home.constants.ErrorConstants;
import com.pn.home.errorHandling.AppException;
import com.pn.home.errorHandling.AppExceptionHandler;

public class CommonUtilities {
	/**
	 * Generate UUID
	 *
	 * @return string - UUID
	 */
	public static String generateUUID() {
		return (UUID.randomUUID().toString());
	}

	/**
	 * Generate timestamp
	 *
	 * @return string - timestamp with format dd-MM-yyyy hh:mm:ss
	 */
	public static String generateTimestamp() {
		final SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_AND_TIME_FORMAT);
		return formatter.format(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * Generate timestamp as Date object
	 *
	 * @return Date - timestamp as Date object
	 */
	public static Date getCurrentDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getHeaderValueFor(Map<String, String> allHeaders, String headerName) {
		return allHeaders.get(headerName);
	}

	/**
	 * Transform string to Date
	 * 
	 * @param format     - string - format e.g. "dd-MM-yyyy hh:mm:ss"
	 * @param dateString - string - dateString to convert
	 *
	 * @return Date - string dateString as Date
	 */
	public static Date getDateFromString(String format, String dateString) throws ParseException {
		return new SimpleDateFormat(format).parse(dateString);
	}

	/**
	 * Transform Date to String date only (format: dd-MM-yyyy)
	 * 
	 * @param date - Date
	 *
	 * @return String date as String
	 */
	public static String convertDateToStringExclTime(Date date) {
		final SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT);
		return formatter.format(date);
	}

	/**
	 * Transform Date to String date only (format: dd-MM-yyyy hh:mm:ss)
	 * 
	 * @param date - Date
	 *
	 * @return String date as String
	 */
	public static String convertDateToStringInclTime(Date date) {
		final SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_AND_TIME_FORMAT);
		return formatter.format(date);
	}

	/**
	 * Transform Date to String date only (format: dd-MM-yyyy hh:mm:ss)
	 * 
	 * @param date - Date
	 *
	 * @return String date as String
	 */
	public static String convertDateToStringI(Date date) {
		final SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT);
		return formatter.format(date);
	}

	/**
	 * Transform Date to String, format input
	 * 
	 * @param date   - Date
	 * @param format - date format e.g. dd/MM/yyyy
	 *
	 * @return String date as String
	 */
	public static String convertDateToString(String format, Date date) {
		final SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * Concatenate values from list of strings to a string comma separated e.g.
	 * {"a", "b", "c"} -> "a, b, c"
	 * 
	 * @param list - List of String - List of string values to concatenate
	 *
	 * @return String values - concatenated values from a list
	 */
	public static String convertListToStringComaSeparatedValues(List<String> list) {
		String values = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				;
				values = values + list.get(i);
			} else {
				values = values + list.get(i) + AppConstants.COMMA_DELIMITER + AppConstants.SPACE;
			}
		}
		return values;
	}


	/**
	 * Check if string value is numeric
	 * 
	 * @param string - field to verify if numeric
	 *
	 * @return Boolean
	 */
	private static Boolean isNumeric(String field) {
		if (field == null || field.isEmpty()) {
			return false;
		}
		try {
			Integer.parseInt(field);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	/**
	 * Helper to get string as datetime object in SQL. 06/04/2020 -> 2020-04-06
	 * 00:00:00.000 by using CONVERT() SQL
	 * 
	 * @param date - String date to convert
	 * 
	 * @return String formatted date
	 **/
	public static String setSqlDate(String date) throws AppException {
		try { // check if valid date passed as parameter
			Date dateObj = CommonUtilities.getDateFromString(AppConstants.DATE_FORMAT, date);
			return CommonUtilities.convertDateToString(AppConstants.SQL_DATE_FORMAT, dateObj);
		} catch (ParseException e) {
			throw AppExceptionHandler.handleException(AppConstants.EMPTY_STRING, ErrorConstants.AUT0001,
					ErrorConstants.AUT0001, new Exception());
		}
	}

	public static String getDBSecret(String secret) throws UnknownHostException {
		String locHostName = InetAddress.getLocalHost().getHostName();
		StringBuffer secretToGet = new StringBuffer();
		// LoggerFactory.getLogger(AppLogger.class).info("\n\n REPORTING HOST URL - {}",
		// locHostName);

		if (locHostName.toLowerCase().contains(AppConstants.DEV_ENV)) {
			secretToGet.append(secret).append(AppConstants.DEV_ENV);
		} else if (locHostName.toLowerCase().contains(AppConstants.TST_ENV)) {
			secretToGet.append(secret).append(AppConstants.TST_ENV);
		} else if (locHostName.toLowerCase().contains(AppConstants.PRD_ENV)) {
			secretToGet.append(secret).append(AppConstants.PRD_ENV);
		} else {
			secretToGet.append(secret).append(AppConstants.DEV_ENV);
		}

		return secretToGet.toString();
	}

	public static String getEnvLocation() throws UnknownHostException {
		String locHostName = InetAddress.getLocalHost().getHostName();
		String envLocation = "";
		// LoggerFactory.getLogger(AppLogger.class).info("\n\n REPORTING HOST URL - {}",
		// locHostName);

		if (locHostName.toLowerCase().contains(AppConstants.DEV_ENV)) {
			envLocation = AppConstants.DEV_ENV;
		} else if (locHostName.toLowerCase().contains(AppConstants.TST_ENV)) {
			envLocation = AppConstants.TST_ENV;
		} else if (locHostName.toLowerCase().contains(AppConstants.PRD_ENV)) {
			envLocation = AppConstants.PRD_ENV;
		} else {
			envLocation = AppConstants.DEV_ENV;
		}

		return envLocation;
	}
}
