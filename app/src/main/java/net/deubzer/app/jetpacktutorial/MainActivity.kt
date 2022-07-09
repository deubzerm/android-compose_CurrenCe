package net.deubzer.app.jetpacktutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.deubzer.app.jetpacktutorial.ui.theme.JetpacktutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Text("Hello world!")
            JetpacktutorialTheme {
                //MessageCard(message = "positin am 23.6.2022 um 14:43", info = "48.23N 12.3E")
                CurrencyMain()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Row(){
        Text(text = "Hello $name!", modifier = Modifier.align(Alignment.Bottom))
        Text(text = "whhaa $name", modifier = Modifier.align(Alignment.Bottom))
    }
}


@Composable
fun MessageCard(message: String, info: String) {
    Row(
        Modifier
            .background(Color.White)
            .clip(RoundedCornerShape(15))
    ) {
        Image(
            painter = painterResource(id = R.drawable.vitoshakite),
            contentDescription = "image for test",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {

            Text(text = message, fontSize = 36.sp, lineHeight = 40.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
            ) {
                Text(
                    text = info,
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                        .background(MaterialTheme.colorScheme.background),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpacktutorialTheme {
        MessageCard(message = "bob am 12.4.2022 um 13.33", info = " 12E 14N")
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    JetpacktutorialTheme {
        MessageCard(message = "Rabbitmq könnte HIER was schreiben!", info = "48.1N 10.2E")
    }
}

@Preview
@Composable
fun PreviewCurrencyMain(){
    JetpacktutorialTheme {
        CurrencyMain()
    }
}

@Composable
fun CurrencyMain() {
    var currencyConverted = "0"
    Column {
        CurrencyInput(currencyConverted)
        Spacer(modifier = Modifier.height(40.dp))
        CurrencyConverted((currencyConverted.toInt() * 0.51).toString())
    }
}

@Composable
private fun CurrencyConverted(currencyConverted: String) {
    var currencyConverted1 = currencyConverted
    OutlinedTextField(
        value = "text",
        onValueChange = { currencyConverted1 = it },
        label = { Text("€uro") },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewCurrencyInput(){
    JetpacktutorialTheme() {
        CurrencyInput("23")
    }
}

@Composable
fun CurrencyInput(currencyConverted: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("лева") },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}


