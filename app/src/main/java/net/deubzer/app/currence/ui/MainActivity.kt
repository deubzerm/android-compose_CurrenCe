package net.deubzer.app.currence.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.deubzer.app.currence.CurrenCeApplication
import net.deubzer.app.currence.R
import net.deubzer.app.currence.components.RadioButtonCurrencyChoice
import net.deubzer.app.currence.data.CurrencyRateRepository
import net.deubzer.app.currence.ui.theme.CurrenCeTheme
import net.deubzer.app.currence.ui.theme.PADDING_DEFAULT
import net.deubzer.app.currence.util.CurrencyEnum
import net.deubzer.app.currence.viewmodel.CurrencyViewModel

class MainActivity : ComponentActivity() {

    //private val viewModel: CurrencyViewModel by viewModels { CurrencyViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        val appContainer = (application as CurrenCeApplication).container
        Log.i("BRANCH", "Branch used for Build" + getString(R.string.gitBranch))
        super.onCreate(savedInstanceState)
        setContent {
            CurrenCeTheme {

                    CurrencyMain(appContainer.currenyMainViewModel)
            }
        }
    }
}


//@Preview(
//    //uiMode = Configuration.UI_MODE_NIGHT_YES,
//    uiMode = 1,
//    name = "Main",
//    showSystemUi = true,
//    showBackground = true
//)
@Preview("Screen", showSystemUi = true, showBackground = true)
@Preview(
    "Screen (dark)",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewCurrencyMain() {
    val repository = CurrencyRateRepository

    val viewModel = CurrencyViewModel(repository)
    viewModel.currencyTop.value = CurrencyEnum.LEW
    viewModel.currencyBottom.value = CurrencyEnum.EUR
    CurrenCeTheme {
        CurrencyMain(viewModel)
    }
}

@Composable
fun CurrencyMain(viewModel: CurrencyViewModel) {


    val onDismissSnackBarState by rememberUpdatedState(newValue = viewModel.isSnackBarShowing)

    if(onDismissSnackBarState){
        val snackBarMessage = "Message"
        //LaunchedEffect(
    }
    val col = Color.hsl(202f,.93f,.82f,)
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(col),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavHost(navController = navController, startDestination = Home.route ){
            composable(route = Home.route){Home.screen}
            composable(route = AddCurrency.route){AddCurrency.screen}
        }
        RadioButtonCurrencyChoice(choices = listOf("Lewa", "Euro", "Dinar", "Shekel", "Lira"), viewModel, 1, viewModel.currencyTop.value.ordinal)
        CurrencyTopInput(viewModel)
        Spacer(modifier = Modifier.height(40.dp))
        CurrencyBottomInput(viewModel)
        RadioButtonCurrencyChoice(choices = listOf("Lewa", "Euro", "Dinar", "Shekel", "Lira"), viewModel, 2,viewModel.currencyBottom.value.ordinal)
        Button(onClick = { /*TODO*/ }) {
            
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyTopInput(currencyVM: CurrencyViewModel) {
    OutlinedTextField(
        value = currencyVM.amountTop.value,
        onValueChange = {
            currencyVM.changeTop(it.replace(",", ".").toFloatOrNull())
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        maxLines = 1,
        label = {
            Text(
                text = currencyVM.currencyTop.value.name
            )
             },
        modifier = Modifier
            .fillMaxWidth()
            .padding(PADDING_DEFAULT),
        trailingIcon = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "clear text",
                modifier = Modifier
                    .clickable {
                        currencyVM.clear()
                    }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrencyBottomInput(currencyVM: CurrencyViewModel) {

    OutlinedTextField(
        value = currencyVM.amountBottom.value,

        onValueChange = {
            currencyVM.changeBottom(it.replace(",", ".").toFloatOrNull())
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,
        label = {
            Text(
                text = currencyVM.currencyBottom.value.name
        ) },
        trailingIcon = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "clear text",
                modifier = Modifier
                    .clickable {
                        currencyVM.clear()
                    }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(PADDING_DEFAULT)
    )

}








