package net.deubzer.app.currence.data

interface ICurrencyRateRepository {

    fun getDefaultExchange(): Float

}