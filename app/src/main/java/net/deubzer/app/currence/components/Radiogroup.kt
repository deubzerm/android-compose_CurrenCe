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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.deubzer.app.currence.data.CurrencyRateRepository
import net.deubzer.app.currence.ui.theme.CurrenCeTheme
import net.deubzer.app.currence.viewmodel.CurrencyViewModel

@Composable
@Preview
fun PreviewRadioButtonSample() {
    CurrenCeTheme {
        val vm = CurrencyViewModel(CurrencyRateRepository)
        RadioButtonCurrencyChoice(listOf("Lewa","Euro"), vm, 1, 0)
    }
}

@Composable
//RadioSettermode: 1 = From, 2 = To
fun RadioButtonCurrencyChoice(choices: List<String>, viewModel: CurrencyViewModel, radioSetterMode: Int, initialChoice: Int) {
    val PADDING_DEFAULT = 12.dp
    val radioOptions = choices
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[initialChoice] ) }
    Row {
        radioOptions.forEach { text ->
                Row(
                    Modifier.selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            viewModel.onclickRadio(radioSetterMode, text)
                                  },
                        role = Role.RadioButton
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge.merge(),
                    modifier = Modifier.padding(start= PADDING_DEFAULT,end = PADDING_DEFAULT)
                )
            }
        }
    }
}