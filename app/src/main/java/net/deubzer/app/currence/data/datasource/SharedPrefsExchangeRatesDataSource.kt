package net.deubzer.app.currence.data.datasource

import android.app.Application
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import net.deubzer.app.currence.R
import net.deubzer.app.currence.data.ExchangeOperations
import net.deubzer.app.currence.util.CurrencyEnum

@Singleton
class SharedPrefsExchangeRatesDataSource @Inject constructor(
  @ApplicationContext private val context: Context,
) : ExchangeOperations {

  val sharedPref = context.getSharedPreferences(prefname, Context.MODE_PRIVATE)

  override fun getExchangeRate(rateKey: Pair<CurrencyEnum, CurrencyEnum>): Float {
    val search =
        sharedPref.getString(rateKey.first.name + "_" + rateKey.second.name, "0f")?.toFloat()
    return search ?: 0F
  }

  override fun updateExchangeRate(ratepair: Pair<CurrencyEnum, CurrencyEnum>, rate: Float) {
    with(sharedPref.edit()) {
      putString(ratepair.first.name + "_" + ratepair.second.name, rate.toString())
      apply()
    }
  }

  companion object {
    const val prefname = "net.deubzer.app.currence.PREFERENCE_FILE_KEY"
  }
}
