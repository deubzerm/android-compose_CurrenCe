package net.deubzer.app.currence

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.deubzer.app.currence.data.CurrencyRateRepository
import net.deubzer.app.currence.ui.CurrencyChangeFactor
import net.deubzer.app.currence.ui.CurrencyMain
import net.deubzer.app.currence.util.CurrencyEnum
import net.deubzer.app.currence.viewmodel.CurrencyViewModel

@Composable
fun Navigation(viewModel: CurrencyViewModel) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Screen.MainScreen.route ){
        composable(route = Screen.MainScreen.route){
            CurrencyMain(viewModel = viewModel, branchInfo = "blub", navController = navController)
        }
        composable(
            route = Screen.ChangeCurrencyFactorScreen.route,
            arguments = listOf(
                navArgument("factorA"){
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("factorB"){
                    type = NavType.StringType
                    nullable = true
                } ) ){
                        CurrencyChangeFactor(
                            factorA = CurrencyRateRepository.getExchangeRate(
                                Pair(CurrencyEnum.LEW, CurrencyEnum.EUR)).toString(),
                            factorB = CurrencyRateRepository.getExchangeRate(
                                Pair(CurrencyEnum.EUR, CurrencyEnum.LEW)).toString(),
                            navController)

        }
    }
}


