package net.deubzer.app.currence.viewmodel

import android.util.Log
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

    val currencyFrom = mutableStateOf(CurrencyEnum.LEW)
    val currencyTo = mutableStateOf(CurrencyEnum.EUR)



    private fun addExchangeRate(ratepair : Pair<CurrencyEnum,CurrencyEnum>, value: Float){
        currencyRateRepository.addExchangeRate(ratepair,value)
    }

    private fun reCalcTop() {
        this.amountTop.value = calculateCurrencies(
            amountTop.value.toConvertableFloat(), Pair(currencyFrom.value, currencyTo.value)
        ).first.toString()
        this.amountBottom.value = calculateCurrencies(
            amountTop.value.toConvertableFloat(), Pair(currencyFrom.value, currencyTo.value)
        ).second.toString()
    }

    private fun reCalcBottom() {
        this.amountBottom.value = calculateCurrencies(
            amountBottom.value.toConvertableFloat(), Pair(currencyTo.value, currencyFrom.value)
        ).first.toString()
        this.amountTop.value = calculateCurrencies(
            amountBottom.value.toConvertableFloat(), Pair(currencyTo.value, currencyFrom.value)
        ).second.toString()
    }


    fun clear() {
        this.amountBottom.value = ""
        this.amountTop.value = ""
    }


    fun setCurrencyFrom(text: String) {
        when (text) {
            "Lewa" -> currencyFrom.value = CurrencyEnum.LEW
            "Euro" -> currencyFrom.value = CurrencyEnum.EUR
            "Dinar" -> currencyFrom.value = CurrencyEnum.DIN
        }
        reCalcTop()
    }


    fun setCurrencyTo(text: String) {
        when (text) {
            "Lewa" -> currencyTo.value = CurrencyEnum.LEW
            "Euro" -> currencyTo.value = CurrencyEnum.EUR
            "Dinar" -> currencyTo.value = CurrencyEnum.DIN
        }
        reCalcBottom()
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
            1 -> setCurrencyFrom(value)
            2 -> setCurrencyTo(value)
        }
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