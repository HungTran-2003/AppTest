package haui.do_an.apptest.presentation.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import haui.do_an.apptest.R
import haui.do_an.apptest.domain.models.Address

@Composable
fun ItemAddress(
    address: Address,
    keyword: String,
    onItemClick: (Address) -> Unit){
    Row(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = "Location"
        )
        Spacer(modifier = Modifier.width(16.dp))

        HighLightSearchKeyWords(
            address.display_name,
            keyword,
            modifier = Modifier.weight(1f))

        IconButton(
            onClick = {onItemClick(address)}
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_direction),
                contentDescription = "Direction"
            )
        }
    }
}

@Composable
fun HighLightSearchKeyWords(
    text: String,
    keyword: String,
    modifier: Modifier = Modifier){
    val highlights = buildAnnotatedString {
        val startHighlightIndex = text.indexOf(keyword, ignoreCase = true)
        if (startHighlightIndex >= 0){
            append(text.substring(0, startHighlightIndex))

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ) {append(text.substring(startHighlightIndex, startHighlightIndex + keyword.length))}

            append(text.substring(startHighlightIndex + keyword.length))
        } else {
            append(text)
        }
    }

    Text(
        text = highlights,
        fontSize = 16.sp,
        modifier = modifier
    )
}