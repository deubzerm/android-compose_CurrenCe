@file:Suppress("UNUSED_PARAMETER", "UNUSED_EXPRESSION")

package net.deubzer.app.jetpacktutorial.components

import android.widget.Button
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.deubzer.app.jetpacktutorial.ui.theme.JetpacktutorialTheme

//@Composable
//public fun AlertDialog(
//    onDismissRequest: () -> Unit,
//confirmButton: @Composable () → Unit,
//dismissButton: @Composable (() → Unit)?,
//title: @Composable (() → Unit)?,
//text: @Composable (() → Unit)?,
//// ...
//): Unit

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    title: @Composable () -> Unit,
    text: @Composable () -> Unit
){
}
//
//https://blog.logrocket.com/adding-alertdialog-jetpack-compose-android-apps/
@Composable
fun SimpleAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
    ) {
    if(show){
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = { onConfirm }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss }) {
                    Text(text = "Cancel")
                }
            },
            title = { Text(text = "Title") },
            text =  { Text(text = "text") }
        )
    }

}

@Composable
fun TextButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
}

@Preview(name = "textbutton")
@Composable
fun PreviewTextButton (){
    JetpacktutorialTheme {
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "ok")
        }
    }
}

@Preview(name = "SimplAlertDialog")
@Composable
fun PreviewAlertDialog (){
    JetpacktutorialTheme {
        SimpleAlertDialog(show = true, onDismiss = { /*TODO*/ }) {

        }
    }
}