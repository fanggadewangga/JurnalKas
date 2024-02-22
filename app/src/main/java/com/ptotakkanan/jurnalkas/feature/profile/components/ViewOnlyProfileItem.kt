package com.ptotakkanan.jurnalkas.feature.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppText

@Composable
fun ViewOnlyProfileItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        AppText(
            text = title,
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.raleway)),
                fontSize = 12.sp,
                fontWeight = FontWeight(500)
            ),
            color = Color(0xFF003566)
        )
        AppText(
            text = value,
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = 12.sp,
                fontWeight = FontWeight(500)
            ),
            color = Color(0xFF003566)
        )
    }
}