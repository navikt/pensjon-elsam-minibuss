package no.nav.pensjon.elsam.minibuss;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.sql.Date.valueOf;

/**
 * Utility for localized parsing and formatting of dates.
 */
public final class DateUtil {
	/** Legal date formats */
	private static final String WID_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static final String WID_DATE_FORMAT_NO_MILLIS = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	private static final String DATE_FORMAT = "dd.MM.yyyy";

	/** Representation of ETERNITY (31.12.9999). */
	public static final Date ETERNITY = valueOf("9999-12-31");

	/**
	 * Parse a string in format yyyy-MM-dd'T'HH:mm:ss'Z' GMT-time into a Date locale time
	 * 
	 * @param input
	 *            the String to parse
	 * @return a Date, null if input is null or an empty String
	 * @throws IllegalArgumentException
	 *             if input is not legal.
	 */
	public static Date parseWIDString(final String input) throws IllegalArgumentException {

		Date output = null;

		if (!StringUtils.hasText(input)) {
			return output;
		} else {
			try {
				// Workaround for handling datetime's without milliseconds (.SSS)
				// checks if '.' is part of the input datestring
				String format = WID_DATE_FORMAT;
				if (input.length() > 19 && !".".equalsIgnoreCase(input.substring(19, 20))) {
					format = WID_DATE_FORMAT_NO_MILLIS;
				}
				// parse to a date in GMT-time, then change to local-time through calendar.
				DateFormat dateFormat = createDateFormat(format);

				dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
				Date gmtDate = dateFormat.parse(input);
				Calendar cal = Calendar.getInstance(TimeZone.getDefault());
				cal.setTime(gmtDate);
				return cal.getTime();
			} catch (ParseException pe) {
				throw new IllegalArgumentException("Failed to parse " + input + ": " + pe);
			}
		}
	}

	/**
	 * Format a Date into a localized String (dd.MM.yyyy).
	 *
	 * @param input
	 *            the date to format.
	 * @return the formatted date or empty string for null input or "eternity" (31.12.9999).
	 * @see #ETERNITY
	 */
	public static String format(final Date input) {
		if (null == input || ETERNITY.equals(input)) {
			return null;
		} else {
			return createDateFormat(DATE_FORMAT).format(input);
		}
	}

	/**
	 * Formats a Date into WID format, in GMT-time (XSD-DateTime: yyyy-MM-dd'T'HH:mm:ss'Z').
	 *
	 * @param input
	 *            the date to format.
	 * @return the formatted date.
	 *
	 *
	 */
	public static String formatWIDString(final Date input) {
		if (input == null) {
			return null;
		}
		DateFormat dateFormat = createDateFormat(WID_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return dateFormat.format(input);
	}

	/**
	 * Creates a non-lenient <code>DateFormat</code> from the specified date format.
	 *
	 * @param dateFormat
	 *            the date format the created <code>DateForm</code> will have.
	 * @return a non-lenient <code>DateFormat</code> with the specified date format.
	 */
	public static DateFormat createDateFormat(final String dateFormat) {
		SimpleDateFormat format = (SimpleDateFormat) DateFormat.getDateInstance();
		format.setLenient(false);
		format.applyLocalizedPattern(dateFormat);

		return format;
	}
}
