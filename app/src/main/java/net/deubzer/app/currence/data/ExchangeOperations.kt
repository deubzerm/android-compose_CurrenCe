package net.deubzer.app.currence.data

import net.deubzer.app.currence.util.CurrencyEnum

interface ExchangeOperations {

  fun getExchangeRate(rateKey: Pair<CurrencyEnum, CurrencyEnum>): Float

  fun updateExchangeRate(ratepair: Pair<CurrencyEnum, CurrencyEnum>, rate: Float)
}
