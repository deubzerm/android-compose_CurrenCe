package net.deubzer.app.currence.data

import android.content.Context
import net.deubzer.app.currence.util.CurrencyEnum

class ExchangeFactorDataStoreSharedPreferences(context: Context) : ExchangeOperations {

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
