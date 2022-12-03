@file:Suppress("UNUSED_PARAMETER", "unused")

package net.deubzer.app.currence.data

import net.deubzer.app.currence.util.CurrencyEnum


private const val TAG = "CurrencyExchangeRepository"

object CurrencyRateRepository {

    private val exchangeDictionary = mutableMapOf<Pair<CurrencyEnum,CurrencyEnum>, Float> (
        Pair(CurrencyEnum.EUR, CurrencyEnum.LEW) to 1.96f,
        Pair(CurrencyEnum.LEW, CurrencyEnum.EUR) to 0.51f,

        Pair(CurrencyEnum.EUR, CurrencyEnum.NIS) to 5.58f,
        Pair(CurrencyEnum.NIS, CurrencyEnum.EUR) to 0.28f,

        Pair(CurrencyEnum.EUR, CurrencyEnum.DIN) to 117.31f,
        Pair(CurrencyEnum.DIN, CurrencyEnum.EUR) to 0.0085f,

        Pair(CurrencyEnum.EUR, CurrencyEnum.TRY) to 19.63f,
        Pair(CurrencyEnum.TRY, CurrencyEnum.EUR) to 0.051f,
    )

    fun getExchangeRate(rateKey : Pair<CurrencyEnum,CurrencyEnum>): Float{
        return exchangeDictionary[rateKey].toValueIfUnfound(rateKey)
    }


    /**
     * If same keys, conversion will be 1, if unfound return 0
     */
    private fun Float?.toValueIfUnfound(exchangeKey: Pair<CurrencyEnum, CurrencyEnum>): Float =
        if (exchangeKey.first == exchangeKey.second)
            1f
        else
            this ?: 0f

    fun addExchangeRate(ratepair: Pair<CurrencyEnum, CurrencyEnum>, rate: Float) {
        if(exchangeDictionary.containsKey(ratepair)){
            exchangeDictionary.replace(ratepair,rate)
        }
        else
            exchangeDictionary.putIfAbsent(ratepair,rate)
    }

    fun updateExchangeRate(ratepair: Pair<CurrencyEnum, CurrencyEnum>, rate: Float) {
        exchangeDictionary.replace(ratepair,rate)
    }

}