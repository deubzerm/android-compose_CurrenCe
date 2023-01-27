package net.deubzer.app.currence.di

import android.content.Context
import net.deubzer.app.currence.data.CurrencyRateRepository
import net.deubzer.app.currence.viewmodel.CurrencyViewModel

interface AppContainer {

    val currencyRateRepository: CurrencyRateRepository

    val currenyMainViewModel: CurrencyViewModel

}

class AppContainerImpl(private val appContext: Context): AppContainer{
    override val currencyRateRepository: CurrencyRateRepository by lazy{
       CurrencyRateRepository
    }
    override val currenyMainViewModel: CurrencyViewModel by lazy {
        CurrencyViewModel(currencyRateRepository)
    }
}