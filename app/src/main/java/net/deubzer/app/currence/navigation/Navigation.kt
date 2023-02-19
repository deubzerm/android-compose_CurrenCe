package net.deubzer.app.currence.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.deubzer.app.currence.ui.CurrencyChangeFactor
import net.deubzer.app.currence.ui.CurrencyMain
import net.deubzer.app.currence.viewmodel.CurrencyChangeViewModel
import net.deubzer.app.currence.viewmodel.CurrencyViewModel

@Composable
fun Navigation(
    startDestination: String = Screen.MainScreen.route,
) {
  val navController = rememberNavController()

  NavHost(
      navController = navController,
      startDestination = startDestination,
  ) {
    composable(route = Screen.MainScreen.route) {
      val mainVM = hiltViewModel<CurrencyViewModel>()
      CurrencyMain(viewModel = mainVM, branchInfo = "blub", navController = navController)
    }
    composable(
        route = Screen.ChangeCurrencyFactorScreen.route,
        arguments =
            listOf(
                navArgument("factorA") {
                  type = NavType.StringType
                  nullable = true
                },
                navArgument("factorB") {
                  type = NavType.StringType
                  nullable = true
                },
            ),
    ) {
        val vm = hiltViewModel<CurrencyChangeViewModel>()
        CurrencyChangeFactor(
            vm,
            navController,
        )
    }
  }
}
