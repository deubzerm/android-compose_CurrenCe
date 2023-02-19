package net.deubzer.app.currence.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.deubzer.app.currence.components.RadioButtonCurrencyChoice
import net.deubzer.app.currence.navigation.Screen
import net.deubzer.app.currence.ui.theme.CurrenCeTheme
import net.deubzer.app.currence.ui.theme.PADDING_DEFAULT
import net.deubzer.app.currence.util.CurrencyEnum
import net.deubzer.app.currence.viewmodel.CurrencyViewModel

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightMode",
)
@Preview(
    name = "DarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewCurrencyMain() {
  val mainVM = hiltViewModel<CurrencyViewModel>()

  val navCo = rememberNavController()

  mainVM.currencyTop.value = CurrencyEnum.LEW
  mainVM.currencyBottom.value = CurrencyEnum.EUR
  CurrenCeTheme {
    Surface { CurrencyMain(mainVM, "Build from branch:  main, Version: TEST", navCo) }
  }
}

@Composable
fun CurrencyMain(viewModel: CurrencyViewModel, branchInfo: String, navController: NavController) {
  Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Spacer(modifier = Modifier.height(15.dp))
    Button(
        onClick = { navController.navigate(Screen.ChangeCurrencyFactorScreen.route) },
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .size(180.dp),
    ) {
      Image(
          painter =
              painterResource(id = net.deubzer.app.currence.R.mipmap.ic_currence_logo_foreground),
          contentDescription = "CurrenCe App Logo",
          modifier = Modifier
              .size(180.dp)
              .clip(CircleShape),
          // .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
          // .align(alignment = CenterHorizontally)
      )
    }
    // Text(text = "CurrenCe")
    Spacer(modifier = Modifier.weight(0.3f, true))
    RadioButtonCurrencyChoice(
        choices = listOf("Lewa", "Euro"),
        viewModel,
        1,
        viewModel.currencyTop.value.ordinal,
    )
    CurrencyTopInput(viewModel)
    Spacer(modifier = Modifier.height(40.dp))
    CurrencyBottomInput(viewModel)
    RadioButtonCurrencyChoice(
        choices = listOf("Lewa", "Euro"),
        viewModel,
        2,
        viewModel.currencyBottom.value.ordinal,
    )
    Spacer(modifier = Modifier.weight(0.6f, true))
    Text(text = branchInfo)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyTopInput(currencyVM: CurrencyViewModel) {
  OutlinedTextField(
      value = currencyVM.amountTop.value,
      onValueChange = { currencyVM.changeTop(it.replace(",", ".").toFloatOrNull()) },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
      maxLines = 1,
      label = {
        Text(
            text = currencyVM.currencyTop.value.name,
        )
      },
      modifier = Modifier
          .fillMaxWidth()
          .padding(PADDING_DEFAULT),
      trailingIcon = {
        Icon(
            Icons.Default.Clear,
            contentDescription = "clear text",
            modifier = Modifier.clickable { currencyVM.clear() },
        )
      },
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrencyBottomInput(currencyVM: CurrencyViewModel) {
  OutlinedTextField(
      value = currencyVM.amountBottom.value,
      onValueChange = { currencyVM.changeBottom(it.replace(",", ".").toFloatOrNull()) },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      maxLines = 1,
      label = {
        Text(
            text = currencyVM.currencyBottom.value.name,
        )
      },
      trailingIcon = {
        Icon(
            Icons.Default.Clear,
            contentDescription = "clear text",
            modifier = Modifier.clickable { currencyVM.clear() },
        )
      },
      modifier = Modifier
          .fillMaxWidth()
          .padding(PADDING_DEFAULT),
  )
}
