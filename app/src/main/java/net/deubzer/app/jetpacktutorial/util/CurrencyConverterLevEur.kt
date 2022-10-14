package net.deubzer.app.jetpacktutorial.util

val eur2lev = 1.96f
val lev2eur = 0.51f
fun calcEur (leva: String, currencytype: CurrencyEnum): String {
    return convertCurrency(leva, lev2eur).toString()
}
//https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml
fun calcLev (euro : String): String {
    return convertCurrency(euro, eur2lev).toString()
}

fun convertCurrency(amount: String, factor: Float) : Float {

    try {
        return amount.toFloat() * factor
    } catch (e: Exception) {
        throw e
    }

    //throw IllegalArgumentException("komisch")
}