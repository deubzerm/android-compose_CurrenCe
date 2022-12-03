package net.deubzer.app.currence.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.deubzer.app.currence.data.CurrencyRateRepository
import net.deubzer.app.currence.util.CurrencyEnum
import net.deubzer.app.currence.util.calculateCurrencies


class CurrencyViewModel (private val currencyRateRepository: CurrencyRateRepository) : ViewModel() {

    var isSnackBarShowing: Boolean by mutableStateOf(false)
        private set

    private fun showSnackBar() {
        isSnackBarShowing = true
    }

    fun dismissSnackBar() {
        isSnackBarShowing = false
    }

    //todo: change to string, do parsing in sanitize! Float causes weird typing in the inputfield.
    // probably do a shadow state field
    // idea: store old values in  room
    val amountTop = mutableStateOf("")
    val amountBottom = mutableStateOf("")

    val currencyTop = mutableStateOf(CurrencyEnum.LEW)
    val currencyBottom = mutableStateOf(CurrencyEnum.EUR)



    private fun addExchangeRate(ratepair : Pair<CurrencyEnum,CurrencyEnum>, value: Float){
        currencyRateRepository.addExchangeRate(ratepair,value)
    }

    private fun reCalcTop() {
        this.amountTop.value = calculateCurrencies(
            amountTop.value.toConvertableFloat(), Pair(currencyTop.value, currencyBottom.value)
        ).first.toString()
        this.amountBottom.value = calculateCurrencies(
            amountTop.value.toConvertableFloat(), Pair(currencyTop.value, currencyBottom.value)
        ).second.toString()
    }

    private fun reCalcBottom() {
        this.amountBottom.value = calculateCurrencies(
            amountBottom.value.toConvertableFloat(), Pair(currencyBottom.value, currencyTop.value)
        ).first.toString()
        this.amountTop.value = calculateCurrencies(
            amountBottom.value.toConvertableFloat(), Pair(currencyBottom.value, currencyTop.value)
        ).second.toString()
    }


    fun clear() {
        this.amountBottom.value = ""
        this.amountTop.value = ""
    }

    private fun setCurrency(
        text: String,
        toporfromcurr: MutableState<CurrencyEnum>,
        calculatefunction: () -> Unit
    ) {
        when (text) {
            "Lewa" -> toporfromcurr.value = CurrencyEnum.LEW
            "Euro" -> toporfromcurr.value = CurrencyEnum.EUR
            "Dinar" -> toporfromcurr.value = CurrencyEnum.DIN
            "Shekel" -> toporfromcurr.value = CurrencyEnum.NIS
            "Lira" -> toporfromcurr.value = CurrencyEnum.TRY
        }
        calculatefunction()
    }


    fun changeTop(it: Float?) {
        if (it != null) {
            this.amountTop.value = it.toString()
            reCalcTop()
        }
    }

    fun changeBottom(it: Float?) {
        if (it != null) {
            this.amountBottom.value = it.toString()
            reCalcBottom()
        }
    }

    fun onclickRadio(radioSetterMode: Int, value: String) {
        Log.d("radiogroup set value", "$radioSetterMode: $value")

        when(radioSetterMode){
            1 -> setCurrency(value, getTopCurrency()) { reCalcBottom() }//setCurrencyFrom(value)
            2 -> setCurrency(value, getBotCurrency()) { reCalcTop() }
        }
    }

    private fun getTopCurrency(): MutableState<CurrencyEnum> {
        return currencyTop
    }
    private fun getBotCurrency(): MutableState<CurrencyEnum> {
        return currencyBottom
    }

}

private fun String.toConvertableFloat(): Float {
    if (this.isEmpty()) return 0f
    return this.toFloat()
}

class CurrencyViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            return CurrencyViewModel(CurrencyRateRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}