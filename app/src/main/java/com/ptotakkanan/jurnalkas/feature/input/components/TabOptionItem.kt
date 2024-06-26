package com.ptotakkanan.jurnalkas.feature.input.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.primary20

@Composable
fun TabOptionItem(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    selected: Boolean,
    backgroundColor: Color = primary20,
    shape: Shape = RoundedCornerShape(100.dp),
    selectedTint: Color = Color.White,
    unselectedTint: Color = primary20,
    onSelect: () -> Unit,
) {
    if (selected)
        AppButton(
            backgroundColor = backgroundColor,
            shape = shape,
            onClick = { }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = icon,
                    contentDescription = "Tab icon",
                    colorFilter = ColorFilter.tint(selectedTint),
                    modifier = Modifier.size(16.dp)
                )
                AppText(
                    text = title,
                    color = selectedTint,
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp
                    )
                )
            }
        }
    else
        AsyncImage(
            model = icon,
            contentDescription = "Tab icon",
            colorFilter = ColorFilter.tint(unselectedTint),
            modifier = Modifier
                .size(24.dp)
                .clickable { onSelect.invoke() }
        )
}