package net.deubzer.app.currence.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import net.deubzer.app.currence.ui.theme.JetpacktutorialTheme
import net.deubzer.app.currence.viewmodel.CurrencyViewModel

@Composable
@Preview
fun PreviewRadioButtonSample() {
    JetpacktutorialTheme {
        RadioButtonCurrencyChoice(listOf("Lewa","Euro"), viewModel = hiltViewModel<CurrencyViewModel>(), 1)
    }
}

@Composable
//RadioSettermode: 1 = From, 2 = To
fun RadioButtonCurrencyChoice(choices: List<String>, viewModel: CurrencyViewModel, radioSetterMode: Int) {
    val radioOptions = choices
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
    Row (
        //verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
            ) {
        radioOptions.forEach { text ->
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .selectable(
//                        selected = (text == selectedOption),
//                        onClick = {
//                            onOptionSelected(text)
//                        }
//                    ),
//
//            ) {
                Row(
                    Modifier.selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                RadioButton(
                    selected = (text == selectedOption),
                    //onClick = { onOptionSelected(text) }
                    onClick = { when(radioSetterMode){
                        1 -> viewModel.setCurrencyFrom(selectedOption)
                        2 -> viewModel.setCurrencyTo(selectedOption)
                    } }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge.merge(),
                )
            }
        }
    }
}