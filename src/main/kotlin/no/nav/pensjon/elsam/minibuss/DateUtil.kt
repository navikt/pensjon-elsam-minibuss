package no.nav.pensjon.elsam.minibuss

import org.springframework.util.StringUtils
import java.sql.Date
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val WID_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private const val WID_DATE_FORMAT_NO_MILLIS = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val DATE_FORMAT = "dd.MM.yyyy"

/** Representation of ETERNITY (31.12.9999).  */
val ETERNITY: java.util.Date = Date.valueOf("9999-12-31")

/**
 * Parse a string in format yyyy-MM-dd'T'HH:mm:ss'Z' GMT-time into a Date locale time
 *
 * @param input
 * the String to parse
 * @return a Date, null if input is null or an empty String
 */
fun parseWIDString(input: String): java.util.Date? {
    val output: java.util.Date? = null

    if (!StringUtils.hasText(input)) {
        return output
    } else {
        try {
            // Workaround for handling datetime's without milliseconds (.SSS)
            // checks if '.' is part of the input datestring
            var format = WID_DATE_FORMAT
            if (input.length > 19 && !".".equals(input.substring(19, 20), ignoreCase = true)) {
                format = WID_DATE_FORMAT_NO_MILLIS
            }
            // parse to a date in GMT-time, then change to local-time through calendar.
            val dateFormat = createDateFormat(format)

            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            val gmtDate = dateFormat.parse(input)
            val cal = Calendar.getInstance(TimeZone.getDefault())
            cal.time = gmtDate
            return cal.time
        } catch (pe: ParseException) {
            throw IllegalArgumentException("Failed to parse $input: $pe")
        }
    }
}

/**
 * Format a Date into a localized String (dd.MM.yyyy).
 *
 * @param input
 * the date to format.
 * @return the formatted date or empty string for null input or "eternity" (31.12.9999).
 * @see .ETERNITY
 */
fun format(input: java.util.Date?): String? {
    return if (null == input || ETERNITY == input) {
        null
    } else {
        createDateFormat(DATE_FORMAT)
            .format(input)
    }
}

/**
 * Formats a Date into WID format, in GMT-time (XSD-DateTime: yyyy-MM-dd'T'HH:mm:ss'Z').
 *
 * @param input
 * the date to format.
 * @return the formatted date.
 */
fun formatWIDString(input: java.util.Date?): String? {
    if (input == null) {
        return null
    }
    val dateFormat = createDateFormat(WID_DATE_FORMAT)
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    return dateFormat.format(input)
}

/**
 * Creates a non-lenient `DateFormat` from the specified date format.
 *
 * @param dateFormat
 * the date format the created `DateForm` will have.
 * @return a non-lenient `DateFormat` with the specified date format.
 */
fun createDateFormat(dateFormat: String): DateFormat {
    val format = DateFormat.getDateInstance() as SimpleDateFormat
    format.isLenient = false
    format.applyLocalizedPattern(dateFormat)

    return format
}
