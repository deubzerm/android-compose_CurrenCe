package net.deubzer.app.currence.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.deubzer.app.currence.ui.theme.CurrenCeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyChangeFactor(factorA: String, factorB: String, navController: NavController) {
  TopAppBar(
      title = { Text(text = "CurrenCe") },
      navigationIcon = {
        IconButton(onClick = { navController.navigateUp() }) { Icon(Icons.Rounded.ArrowBack, "") }
      },
  )
  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    Column {
      Text(text = "from ->  to  factor: $factorA")
      Text(text = "from <-  to  factor: $factorB")
    }
  }
}

@Preview
@Composable
fun PreviewCurrencyChangeFactor() {
  val navCo = rememberNavController()

  CurrenCeTheme { Surface { CurrencyChangeFactor(factorA = "A", factorB = "B", navCo) } }
}
