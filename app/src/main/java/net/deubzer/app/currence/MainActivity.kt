package net.deubzer.app.currence

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.deubzer.app.currence.components.RadioButtonCurrencyChoice
import net.deubzer.app.currence.data.CurrencyRateRepository
import net.deubzer.app.currence.ui.theme.CurrenCeTheme
import net.deubzer.app.currence.ui.theme.PADDING_DEFAULT
import net.deubzer.app.currence.util.CurrencyEnum
import net.deubzer.app.currence.viewmodel.CurrencyViewModel
import net.deubzer.app.currence.viewmodel.CurrencyViewModelFactory

class MainActivity : ComponentActivity() {


    private val viewModel: CurrencyViewModel by viewModels { CurrencyViewModelFactory() }

    fun getBranchInfo(): String {
        return "Build from branch: " + getString(R.string.gitBranch) + ", Version: " + BuildConfig.VERSION_NAME
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("CURRENCE:BRANCH", getBranchInfo())
        super.onCreate(savedInstanceState)
        setContent {
            CurrenCeTheme {
                Surface {
                    CurrencyMain(viewModel, getBranchInfo())
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO,
name="LightMode")
@Preview(
    name="DarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewCurrencyMain() {
    val repository = CurrencyRateRepository

    val viewModel = CurrencyViewModel(repository)
    viewModel.currencyTop.value = CurrencyEnum.LEW
    viewModel.currencyBottom.value = CurrencyEnum.EUR
    CurrenCeTheme {
        Surface {
            CurrencyMain(viewModel, "Build from branch:  main, Version: TEST")
        }
    }
}

@Composable
fun CurrencyMain(viewModel: CurrencyViewModel, branchInfo: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_currence_logo_foreground),
            contentDescription = "CurrenCe App Logo",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                //.border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                .align(alignment = CenterHorizontally)
        )
        //Text(text = "CurrenCe")
        Spacer(modifier = Modifier.weight(0.3f,true))
        RadioButtonCurrencyChoice(choices = listOf("Lewa", "Euro"), viewModel, 1, viewModel.currencyTop.value.ordinal)
        CurrencyTopInput(viewModel)
        Spacer(modifier = Modifier.height(40.dp))
        CurrencyBottomInput(viewModel)
        RadioButtonCurrencyChoice(choices = listOf("Lewa", "Euro"), viewModel, 2,viewModel.currencyBottom.value.ordinal)
        Spacer(modifier = Modifier.weight(0.6f,true))
        Text(text = branchInfo)
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









