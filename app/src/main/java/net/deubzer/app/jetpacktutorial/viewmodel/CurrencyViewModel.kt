package net.deubzer.app.jetpacktutorial.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.deubzer.app.jetpacktutorial.util.calcEur
import net.deubzer.app.jetpacktutorial.util.calcLev
import java.lang.IllegalArgumentException

class CurrencyViewModel() : ViewModel() {

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    //todo: change to string, do parsing in sanitize! Float causes weird typing in the inputfield.
    // probably do a shadow state field
    // idea: store old values in  room
    val amountLev = mutableStateOf("")

    val amountEur = mutableStateOf("")

    init {
       // changeAmountLev(0f)
       // changeAmountEur(0f)
    }

    fun changeAmountLev(amount: String  ) {
        //if(amount.isNotEmpty() or amount.isNotBlank()) return
        this.amountLev.value = amount
        this.amountEur.value = calcEur(amount)
    }

    fun changeAmountEur(amount: String) {
        //if(amount.isNotEmpty() or amount.isNotBlank()) return
        this.amountEur.value = amount
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

}

class CurrencyViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CurrencyViewModel::class.java)){
            return CurrencyViewModel() as T
        }
        throw IllegalArgumentException ( "Unknown ViewModel class")
    }
}