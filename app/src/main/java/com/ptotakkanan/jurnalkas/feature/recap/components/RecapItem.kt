package com.ptotakkanan.jurnalkas.feature.recap.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ptotakkanan.jurnalkas.core.ext.toCurrency
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.Typography

@Composable
fun RecapItem(
    modifier: Modifier = Modifier,
    title: String,
    nominal: Long,
    titleColor: Color = Color.Black,
    nominalColor: Color = Color.Black,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AppText(
            text = title,
            color = titleColor,
            textStyle = Typography.bodyMedium().copy(fontSize = 16.sp)
        )
        AppText(
            text = nominal.toCurrency(),
            color = nominalColor,
            textStyle = Typography.titleMedium()
                .copy(fontSize = 16.sp, fontWeight = FontWeight(500))
        )
    }
}