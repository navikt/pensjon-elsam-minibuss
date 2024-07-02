package no.nav.pensjon.elsam.minibuss.misc

private val fnrRegex = Regex("\\b\\d{11}\\b")

fun redact(string: String): String =
    string.replace(fnrRegex) {
        if (isMod11Compliant(it.value)) {
            "***********"
        } else {
            it.value
        }
    }

fun isMod11Compliant(fnr: String): Boolean {
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
    val v1 = (3 * d1) + (7 * d2) + (6 * m1) + (1 * m2) + (8 * a1) + (9 * a2) + (4 * i1) + (5 * i2) + (2 * i3)

    var tmp = v1 / 11
    val rest1 = v1 - (tmp * 11)
    val kontK1 = if ((rest1 == 0)) 0 else (11 - rest1)

    // control 2
    val v2 = (5 * d1) + (4 * d2) + (3 * m1) + (2 * m2) + (7 * a1) + (6 * a2) + (5 * i1) + (4 * i2) + (3 * i3) + (2 * k1)
    tmp = v2 / 11
    val rest2 = v2 - (tmp * 11)
    val kontK2 = if ((rest2 == 0)) 0 else (11 - rest2)

    // checks that control number is correct
    return kontK1 == k1 && kontK2 == k2
}
