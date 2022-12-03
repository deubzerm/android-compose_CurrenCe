package net.deubzer.app.currence.util

import net.deubzer.app.currence.data.CurrencyRateRepository

val repo = CurrencyRateRepository

//fun calcEur (leva: String, currencytype: CurrencyEnum): String {
//    return convertCurrency(leva, lev2eur).toString()
//}
////https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml
//fun calcLev (euro : String): String {
//    return convertCurrency(euro, eur2lev).toString()
//}

fun convertCurrency(amount: Float, factor: Float): Float {
    return amount * factor
}

/**
 * insert base amount, will return the baseamount converted and unconverted
 */
fun calculateCurrencies(
    baseAmount: Float,
    exchangeKey: Pair<CurrencyEnum, CurrencyEnum>
): Pair<Float, Float> {
    return Pair(
        baseAmount,
        convertCurrency(
            baseAmount,
            repo.getExchangeRate(exchangeKey)
        )
    )
}

