package net.deubzer.app.currence.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.deubzer.app.currence.R

@Composable
fun MessageCard(message: String, info: String) {
  Row(
      Modifier.background(Color.White).clip(RoundedCornerShape(15)),
  ) {
    Image(
        painter = painterResource(id = R.drawable.vitoshakite),
        contentDescription = "image for test",
        modifier =
            Modifier.size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                .align(Alignment.CenterVertically),
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
            modifier =
                Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    .background(MaterialTheme.colorScheme.background),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodySmall,
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  CurrenCeTheme { MessageCard(message = "bob am 12.4.2022 um 13.33", info = " 12E 14N") }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
)
@Composable
fun PreviewMessageCard() {
  CurrenCeTheme {
    MessageCard(message = "Rabbitmq könnte HIER was schreiben!", info = "48.1N 10.2E")
  }
}
