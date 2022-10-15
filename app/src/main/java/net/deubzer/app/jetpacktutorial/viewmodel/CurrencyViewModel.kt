package net.deubzer.app.jetpacktutorial.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.deubzer.app.jetpacktutorial.data.CurrencyExchangeRepository
import net.deubzer.app.jetpacktutorial.data.ExchangeSerializer
import net.deubzer.app.jetpacktutorial.datastore.Exchange
import net.deubzer.app.jetpacktutorial.util.CurrencyEnum
import net.deubzer.app.jetpacktutorial.util.calcEur
import net.deubzer.app.jetpacktutorial.util.calcLev
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder


class CurrencyViewModel() : ViewModel() {



    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    //todo: change to string, do parsing in sanitize! Float causes weird typing in the inputfield.
    // probably do a shadow state field
    // idea: store old values in  room
    val amountLev = mutableStateOf("")

    val amountEur = mutableStateOf("")

    val currencyFrom = mutableStateOf(CurrencyEnum.LEW)
    val currencyTo = mutableStateOf(CurrencyEnum.EUR)

    init {

        // changeAmountLev(0f)
        // changeAmountEur(0f)
    }

    fun changeAmountLev(amount: String) {
        if (amount.isEmpty()) {
            resetCurrencyToZero()
            return
        }
        CalculateCurrencyValues(amount)
    }

    private fun CalculateCurrencyValues(amount: String) {
        this.amountLev.value = amount.EvaluateCalc()
        this.amountEur.value = calcEur(amount, currencyFrom.value)
    }

    private fun resetCurrencyToZero() {
        this.amountLev.value = ""
        this.amountEur.value = ""
    }

    fun changeAmountEur(amount: String) {
        if (amount.isEmpty()) {
            resetCurrencyToZero()
            return
        }
        this.amountEur.value = amount.EvaluateCalc()
        this.amountLev.value = calcLev(amount)
    }

    fun clear() {
        this.amountEur.value = ""
        this.amountLev.value = ""
    }

    fun showCurrencies(): String {
        return "€: " + amountEur.value + "; Lev: " + amountLev.value
    }

    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        _showDialog.value = false
        // Continue with executing the confirmed action
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun validateCurrencyInput(it: String): Boolean {
//        val amnt = it.toFloatOrNull()
//        if(it.isEmpty()) return true
//        return amnt != null
        return true
    }

    fun setCurrencyFrom(text: String) {
        when (text) {
            "Lewa" -> currencyFrom.value = CurrencyEnum.LEW
            "Euro" -> currencyFrom.value = CurrencyEnum.EUR
            "Dinar" -> currencyFrom.value = CurrencyEnum.DIN
        }
        reCalc()
    }

    private fun reCalc() {
        TODO("Not yet implemented")
    }

    fun setCurrencyTo(text: String) {
        when (text) {
            "Lewa" -> currencyTo.value = CurrencyEnum.LEW
            "Euro" -> currencyTo.value = CurrencyEnum.EUR
            "Dinar" -> currencyTo.value = CurrencyEnum.DIN
        }
    }
}

private fun String.EvaluateCalc(): String {


    var txt: String = this

    val expression: Expression = ExpressionBuilder(txt).build()
    try {
        // Calculate the result and display
        val result: Double = expression.evaluate()
        txt = this
    } catch (ex: ArithmeticException) {
        // Display an error message
        return "0"
    }

    return txt

}

class CurrencyViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            return CurrencyViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}