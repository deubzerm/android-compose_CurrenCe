@file:Suppress("UNUSED_PARAMETER", "unused", "RemoveExplicitTypeArguments")

package net.deubzer.app.currence.data

import net.deubzer.app.currence.util.CurrencyEnum


private const val TAG = "CurrencyExchangeRepository"

object CurrencyRateRepository {

    private val exchangeDictionary = mutableMapOf<Pair<CurrencyEnum,CurrencyEnum>, Float> (
        Pair(CurrencyEnum.EUR, CurrencyEnum.LEW) to 1.96f,
        Pair(CurrencyEnum.EUR, CurrencyEnum.NIS) to 5.58f,
        Pair(CurrencyEnum.EUR, CurrencyEnum.DIN) to 117.31f,
        Pair(CurrencyEnum.EUR, CurrencyEnum.TRY) to 19.63f,

        Pair(CurrencyEnum.LEW, CurrencyEnum.EUR) to 0.51f,
        Pair(CurrencyEnum.LEW, CurrencyEnum.NIS) to 1.82f,
        Pair(CurrencyEnum.LEW, CurrencyEnum.DIN) to 59.47f,
        Pair(CurrencyEnum.LEW, CurrencyEnum.TRY) to 9.95f,

        Pair(CurrencyEnum.DIN, CurrencyEnum.EUR) to 0.0085f,
        Pair(CurrencyEnum.DIN, CurrencyEnum.NIS) to 0.031f,
        Pair(CurrencyEnum.DIN, CurrencyEnum.LEW) to 0.017f,
        Pair(CurrencyEnum.DIN, CurrencyEnum.TRY) to 0.17f,


        Pair(CurrencyEnum.TRY, CurrencyEnum.EUR) to 0.051f,
        Pair(CurrencyEnum.TRY, CurrencyEnum.NIS) to 0.18f,
        Pair(CurrencyEnum.TRY, CurrencyEnum.DIN) to 5.97f,
        Pair(CurrencyEnum.TRY, CurrencyEnum.LEW) to 0.10f,


        Pair(CurrencyEnum.NIS, CurrencyEnum.EUR) to 0.28f,
        Pair(CurrencyEnum.NIS, CurrencyEnum.DIN) to 32.85f,
        Pair(CurrencyEnum.NIS, CurrencyEnum.TRY) to 5.50f,
        Pair(CurrencyEnum.NIS, CurrencyEnum.LEW) to 0.55f,
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