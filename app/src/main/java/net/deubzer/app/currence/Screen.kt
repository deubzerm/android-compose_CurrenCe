package net.deubzer.app.currence

sealed class Screen(val route: String) {

    object MainScreen: Screen("main_screen")
    object ChangeCurrencyFactorScreen: Screen("changefactor_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
