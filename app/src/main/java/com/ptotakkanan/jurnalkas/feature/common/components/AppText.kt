package com.ptotakkanan.jurnalkas.feature.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.ptotakkanan.jurnalkas.theme.Typography

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Color.Black,
    textStyle: TextStyle = Typography.bodyMedium(),
    overflow: TextOverflow = TextOverflow.Visible,
    maxLine: Int = Int.MAX_VALUE,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        style = textStyle,
        overflow = overflow,
        maxLines = maxLine
    )
}