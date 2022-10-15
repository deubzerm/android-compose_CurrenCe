package net.deubzer.app.jetpacktutorial.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import net.deubzer.app.jetpacktutorial.datastore.Currency
import net.deubzer.app.jetpacktutorial.datastore.CurrencyRate
import net.deubzer.app.jetpacktutorial.datastore.Exchange
import java.io.IOException

//https://developer.android.com/codelabs/android-proto-datastore#8

class CurrencyRateRepository (private val currencyDataStore: DataStore<CurrencyRate>, context: Context) {

    private val TAG: String = "CurrencyRepository"

    suspend fun saveCurrency(currencyRate: CurrencyRate, from: Currency, to: Currency, factor: Double ){
        currencyDataStore.updateData {
            cr ->
            cr.toBuilder().setFROM(from).setTO(to).setFactor(factor).build()
        }
    }

    val currencyRateFlow: Flow<CurrencyRate> = currencyDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading currencyRate", exception)
                emit(CurrencyRate.getDefaultInstance())
            } else {
                throw exception
            }
        }

}

class CurrencyExchangeRepository(private val currencyExchangeDatastore: DataStore<Exchange>, context: Context){

    private val TAG: String = "CurrencyExchangeRepository"

    suspend fun saveExchange(exchange: Exchange, cr: CurrencyRate){
        currencyExchangeDatastore.updateData { e ->
            e.toBuilder().addExchange(cr).build()
         }
    }

    suspend fun createExchange( cr: CurrencyRate) {
        currencyExchangeDatastore.updateData { e -> e.toBuilder().setExchange(0,cr).build() }
    }

}