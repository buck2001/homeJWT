package com.pn.home.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateFunctions {
	public static String getDateForTodayMinus90Days() {
		LocalDate date = LocalDate.now().minusDays(91);
		String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return formattedDate;
	}

	public static String getDateForToday() {
		LocalDate date = LocalDate.now();
		String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return formattedDate;
	}

	/**
	 * Password lapse after 90 days, check
	 * 
	 * @param lastUpdated
	 * @return boolean
	 **/
	public static boolean checkLapsedPassword(String lastUpdated) {
		SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		long diff = 0;
		Date today = new Date();
		Date lastSavedDate;

		try {
			lastSavedDate = simpleDateFormatter.parse(lastUpdated);
			long diffInMillies = Math.abs(today.getTime() - lastSavedDate.getTime());
			diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (diff >= 90 ? true : false);
	}
}
