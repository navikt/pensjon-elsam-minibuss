package no.nav.pensjon.elsam.minibuss

/**
 * Determines whether the specified string is a valid personal identification number. A valid PID can be: FNR, DNR,
 * BostNr, Dolly number or Tenor number. This method does not check for special circumstances i.e. where the
 * personnummer has a specific value like 00000 or 000001.
 *
 * @param pid
 * personal identification id to validate
 * @return `true` if the specified string is valid, otherwise `false`
 */
fun isValidPid(pid: String?) = pid
    ?.replace(" ", "")
    ?.let {
        isValidCharacters(it) && isValidFnrLength(it)
                && isMod11Compliant(it)
                && (isDnummer(it) || !isSpecialCircumstance(it))
                && isFnrDateValid(makeDateAdjustments(it), isDnummer(it))
    } ?: false

/**
 * Calculates wether the Pid parameter is representing a D-nummer.
 *
 * @param pidValue
 * The Pid to check
 * @return true if it is a D-nummer
 */
private fun isDnummer(pidValue: String) = isDnrDay(getDay(pidValue))

/**
 * Gets the day part of a pid
 */
private fun getDay(validPid: String) = validPid.substring(0, 2).toInt()

/**
 * Gets the Month part of a pid
 */
private fun getMonth(validPid: String) = validPid.substring(2, 4).toInt()

private fun isValidFnrLength(fnr: String?) = fnr?.length == 11

/**
 * Validates that the characters that make up the fnr are valid. To be valid, all characters must be numeric.
 *
 * @param fnr
 * personal identification number
 * @return `true` if valid, otherwise `false`
 */
private fun isValidCharacters(fnr: String) = fnr.toLongOrNull() != null

/**
 * Checks that a fnr is valid according to the modulus 11 control.
 *
 * @param fnr
 * fodselsnummer
 * @return true if fnr is valid, otherwise false
 */
private fun isMod11Compliant(fnr: String): Boolean {
    // FORMAT: DDMMYYXXXYY
    val d1 = fnr.substring(0, 1).toInt()
    val d2 = fnr.substring(1, 2).toInt()
    val m1 = fnr.substring(2, 3).toInt()
    val m2 = fnr.substring(3, 4).toInt()
    val a1 = fnr.substring(4, 5).toInt()
    val a2 = fnr.substring(5, 6).toInt()
    val i1 = fnr.substring(6, 7).toInt()
    val i2 = fnr.substring(7, 8).toInt()
    val i3 = fnr.substring(8, 9).toInt()
    val k1 = fnr.substring(9, 10).toInt()
    val k2 = fnr.substring(10).toInt()

    // control 1
    val v1 = 3 * d1 + 7 * d2 + 6 * m1 + m2 + 8 * a1 + 9 * a2 + 4 * i1 + 5 * i2 + 2 * i3
    var tmp = v1 / 11
    val rest1 = v1 - tmp * 11
    val kontK1 = if (rest1 == 0) 0 else 11 - rest1

    // control 2
    val v2 = 5 * d1 + 4 * d2 + 3 * m1 + 2 * m2 + 7 * a1 + 6 * a2 + 5 * i1 + 4 * i2 + 3 * i3 + 2 * k1
    tmp = v2 / 11
    val rest2 = v2 - tmp * 11
    val kontK2 = if (rest2 == 0) 0 else 11 - rest2

    // checks that control number is correct
    return kontK1 == k1 && kontK2 == k2
}

/**
 * Checks that a fnr is valid special circumstance. A special circumstance is when the personnummer is 0 or 1.
 *
 * @param fnr
 * fodselsnummer
 * @return `true` if fnr is valid special circumstance, otherwise `false`
 */
private fun isSpecialCircumstance(fnr: String) = fnr.substring(6).toInt() in 0..1

/**
 * Checks that a day may be a D-nummer. In a D-nummer 40 is added to the day part of the date
 */
private fun isDnrDay(day: Int) = day in 41..71

private fun adjustDnrDay(day: Int) = day - 40


/**
 * Validates that the first six digits of a fnr represents a valid birth date.
 *
 * @param dnrOrBnrAdjustedFnr
 * - 11 digit fødselsnummer, ajdusted if bnr or fnr
 * @param isDnummer
 * indicates if the dnrOrBnrAdjusteFnr is a Dnr
 * @return `true` if fnr can be converted to a valid date, otherwise `false`
 */
private fun isFnrDateValid(dnrOrBnrAdjustedFnr: String, isDnummer: Boolean): Boolean {
    var validDate = true

    // fnr format is <DDMMAAXXXYY>
    val day = getDay(dnrOrBnrAdjustedFnr)
    val month = getMonth(dnrOrBnrAdjustedFnr)
    val year = get4DigitYearOfBirthWithAdjustedFnr(dnrOrBnrAdjustedFnr, isDnummer)
    val isSpecial = isSpecialCircumstance(dnrOrBnrAdjustedFnr)

    // invalid birth year
    if (year == -1 && !isSpecial) {
        return false
    }
    if (day < 1) {
        validDate = false
    }
    validDate = when (month) {
        1, 3, 5, 7, 8, 10, 12 -> validDate and (day <= 31)
        4, 6, 9, 11 -> validDate and (day <= 30)
        2 ->
            /*
             * Leap year calculation:
             *
             * Rule: If year can be devided by 4, it's a leap year
             *
             * Exeception 1: If also can be divided by 100, it's NOT a leap year Exception 2: If year can be devided by 100 AND
             * 400, it IS a leap year
             */
            if (year == -1) {
                validDate and (day <= 29)
            } else {
                when {
                    year % 100 == 0 && year % 400 == 0 -> validDate and (day <= 29) // leap year
                    year % 100 != 0 && year % 4 == 0 -> validDate and (day <= 29) // det er skuddår
                    else -> validDate and (day <= 28) // det er IKKE skuddår
                }
            }
        else -> false
    }
    return validDate
}

/**
 * Adjusts date so that the first 6 numbers represents a valid date.
 */
private fun makeDateAdjustments(fnr: String) = if (fnr.isBlank()) {
    fnr
} else {
    val day = getAdjustedDay(fnr)
    val month = getAdjustedMonth(fnr)

    fnr.replaceRange(
        0..1,
        "$day".padStart(2, '0')
    ).replaceRange(
        2..3,
        "$month".padStart(2, '0')
    )
}

/**
 * Removes adjustments from the month.
 * - Bost adds 20
 * - Dolly adds 40
 * - Tenor adds 80
 */
private fun getAdjustedMonth(fnr: String) = getMonth(fnr).let {
    when (it) {
        // BOST-number
        in 21..32 -> it - 20
        // Dolly test population
        in 41..52 -> it - 40
        // Tenor test population
        in 81..92 -> it - 80
        else -> it
    }
}

private fun getAdjustedDay(fnr: String): Int = getDay(fnr).let {
    if (isDnrDay(it)) {
        adjustDnrDay(it)
    } else {
        it
    }
}

/**
 * Returns a 4-digit birth date.
 *
 * @param dnrOrBnrAdjustedFnr
 * a fnr, adjusted if it's a bnr or dnr
 * @param isDnummer
 * boolean that says wether the dnrOrBnrAdjustedFnr is a Dnr
 * @return 4 digit birth date, -1 if invalid
 */
private fun get4DigitYearOfBirthWithAdjustedFnr(dnrOrBnrAdjustedFnr: String, isDnummer: Boolean): Int {
    val year = dnrOrBnrAdjustedFnr.substring(4, 6).toInt()
    val individnr = dnrOrBnrAdjustedFnr.substring(6, 9).toInt()

    return year + when {
        !isDnummer && dnrOrBnrAdjustedFnr.substring(6).toInt() < 10 -> return -1 // stilborn baby (dødfødt barn)

        // recalculating year
        individnr < 500 -> 1900
        individnr < 750 && 54 < year -> 1800
        individnr < 1000 && year < 40 -> 2000
        individnr in 900..999 -> 1900

        else -> return -1 // invalid fnr
    }
}