package net.deubzer.app.jetpacktutorial.util

import java.lang.IllegalArgumentException

    val eur2lev = 1.96f
    val lev2eur = 0.51f
fun calcEur (leva : String): String {
    return convertCurrency(leva, lev2eur).toString()
}

fun calcLev (euro : String): String {
    return convertCurrency(euro, eur2lev).toString()
}

fun convertCurrency(amount: String, factor: Float) : Float {

    try {
        return amount.toFloat() * factor
    } catch (e: Exception) {
        e.printStackTrace()
    }

    throw IllegalArgumentException("komisch")
}