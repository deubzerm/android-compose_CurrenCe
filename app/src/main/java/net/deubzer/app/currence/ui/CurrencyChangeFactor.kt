package net.deubzer.app.currence.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.deubzer.app.currence.ui.theme.CurrenCeTheme
import net.deubzer.app.currence.viewmodel.CurrencyChangeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyChangeFactor(viewModel: CurrencyChangeViewModel, navController: NavController) {
  TopAppBar(
      title = { Text(text = "CurrenCe") },
      navigationIcon = {
        IconButton(onClick = { navController.navigateUp() }) { Icon(Icons.Rounded.ArrowBack, "") }
      },
  )
  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    Column {
//      Button(onClick = { /*TODO*/ }) {
//          Text(text = "ChangeMe")
//      }
      Text(text =  "LEW -> EUR : " + viewModel.getLewEur().first, modifier = Modifier.padding(10.dp))
      Text(text = " EUR -> LEW : " + viewModel.getLewEur().second, modifier = Modifier.padding(10.dp))
//      Button(onClick = { /*TODO*/ }) {
//            Text(text = "ChangeMe")
//      }
    }
  }
}

@Preview
@Composable
fun PreviewCurrencyChangeFactor() {
    val vm = hiltViewModel<CurrencyChangeViewModel>()
    val navCo = rememberNavController()
    CurrenCeTheme { Surface { CurrencyChangeFactor(vm, navCo) } }
}
