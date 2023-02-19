package net.deubzer.app.currence.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import net.deubzer.app.currence.data.CurrencyRateRepository

@HiltViewModel
class CurrencyChangeViewModel @Inject constructor(
    private val repo: CurrencyRateRepository
) : ViewModel() {



    fun getLewEur(): Pair<Float,Float> {
       return repo.getInitialExchanges()
    }

}
