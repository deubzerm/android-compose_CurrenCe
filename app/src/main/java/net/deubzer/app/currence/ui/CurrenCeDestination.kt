package net.deubzer.app.currence.ui

import androidx.compose.runtime.Composable
import net.deubzer.app.currence.CurrenCeApplication

interface CurrenCeDestination {
    val route: String
    val screen: @Composable () -> Unit
}
val appContainer = CurrenCeApplication().getCurrenCeContainer()

object Home : CurrenCeDestination{

    override val route = "home"
    override val screen: @Composable () -> Unit ={ CurrencyMain(viewModel = appContainer.currenyMainViewModel)}
}


object AddCurrency : CurrenCeDestination{
    override val route = "addCurrency"
    override val screen: @Composable () -> Unit ={}
}
