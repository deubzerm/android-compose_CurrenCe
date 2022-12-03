package net.deubzer.app.currence.di

import android.content.Context
import net.deubzer.app.currence.data.CurrencyRateRepository

interface AppContainer {

    val currencyRateRepository: CurrencyRateRepository

}

class AppContainerImpl(private val appContext: Context): AppContainer{
    override val currencyRateRepository: CurrencyRateRepository by lazy{
       CurrencyRateRepository
    }
}