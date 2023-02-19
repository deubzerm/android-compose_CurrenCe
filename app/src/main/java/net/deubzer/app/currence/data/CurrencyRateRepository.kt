@file:Suppress("UNUSED_PARAMETER", "unused", "RemoveExplicitTypeArguments")

package net.deubzer.app.currence.data

import javax.inject.Inject
import javax.inject.Singleton
import net.deubzer.app.currence.data.datasource.ApplicationExchangeRatesDataSource
import net.deubzer.app.currence.data.datasource.SharedPrefsExchangeRatesDataSource
import net.deubzer.app.currence.util.CurrencyEnum

private const val TAG = "CurrencyExchangeRepository"

@Singleton
class CurrencyRateRepository @Inject constructor(
    private val spDS : SharedPrefsExchangeRatesDataSource,
    private val appDS : ApplicationExchangeRatesDataSource
) {

  // val datastore = ExchangeFactorDataStoreSharedPreferences(context)
  fun getInitialExchanges() : Pair<Float,Float> {
      return Pair<Float,Float>(
          appDS.getExchangeRate(Pair(CurrencyEnum.LEW,CurrencyEnum.EUR)),
          appDS.getExchangeRate(Pair(CurrencyEnum.EUR,CurrencyEnum.LEW)))
  }

    fun getExchangeRate(exchangeKey: Pair<CurrencyEnum, CurrencyEnum>): Float {
       return appDS.getExchangeRate(exchangeKey)
    }


}
