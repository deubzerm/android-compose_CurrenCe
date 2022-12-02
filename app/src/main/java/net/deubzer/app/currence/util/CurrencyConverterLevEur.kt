package net.deubzer.app.currence.util


val exchangeDictionary = mapOf(
    Pair(CurrencyEnum.EUR, CurrencyEnum.LEW) to 1.96f,
    Pair(CurrencyEnum.LEW, CurrencyEnum.EUR) to 0.51f,

    Pair(CurrencyEnum.EUR, CurrencyEnum.NIS) to 5.58f,
    Pair(CurrencyEnum.NIS, CurrencyEnum.EUR) to 0.28f,

    Pair(CurrencyEnum.EUR, CurrencyEnum.DIN) to 117.31f,
    Pair(CurrencyEnum.DIN, CurrencyEnum.EUR) to 0.0085f,

    Pair(CurrencyEnum.EUR, CurrencyEnum.TRY) to 19.63,
    Pair(CurrencyEnum.TRY, CurrencyEnum.EUR) to 0.051f,
)


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
            (exchangeDictionary[exchangeKey] as Float?).toValueIfUnfound(exchangeKey)
        )
    )
}

/**
 * If same keys, conversion will be 1, if unfound return 0
 */
private fun Float?.toValueIfUnfound(exchangeKey: Pair<CurrencyEnum, CurrencyEnum>): Float =
    if (exchangeKey.first == exchangeKey.second)
        1f
    else
        this ?: 0f



