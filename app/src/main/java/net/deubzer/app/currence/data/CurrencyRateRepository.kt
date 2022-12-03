@file:Suppress("UNUSED_PARAMETER", "unused")

package net.deubzer.app.currence.data

import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

private const val TAG = "CurrencyExchangeRepository"

class CurrencyRateRepository  @Inject constructor() : ICurrencyRateRepository{

    init {
        Log.d(TAG, "initialized CurrencyExchangeRepository")
    }

    override fun getDefaultExchange(): Float {
        return 1f
    }


}

@Module
@InstallIn(ViewModelComponent::class)
abstract class CurrencyRateRepositoryModule {

    @Binds
    abstract fun bindCurrencyRateRepository(
        currencyRateRepository: CurrencyRateRepository
    ): ICurrencyRateRepository
}