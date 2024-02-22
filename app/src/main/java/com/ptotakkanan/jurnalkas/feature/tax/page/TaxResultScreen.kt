package com.ptotakkanan.jurnalkas.feature.tax.page

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.core.ext.toCurrency
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.tax.TaxEvent
import com.ptotakkanan.jurnalkas.feature.tax.TaxState
import com.ptotakkanan.jurnalkas.feature.tax.TaxViewModel
import com.ptotakkanan.jurnalkas.feature.tax.components.TaxInputItem

@Composable
fun TaxResultScreen(
    navController: NavController,
    viewModel: TaxViewModel,
    state: TaxState
) {
    Spacer(modifier = Modifier.height(24.dp))
    AppText(
        text = "Hitung Pajakmu",
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.raleway_bold)),
            fontSize = 24.sp
        )
    )

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TaxInputItem(
            title = "Jumlah Penghasilan",
            content = {
                BasicTextField(
                    value = state.salary.toCurrency(),
                    onValueChange = {  },
                    enabled = false,
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 12.dp)
        )
        TaxInputItem(
            title = "Gaji Bersih (Take-Home-Salary)",
            content = {
                BasicTextField(
                    value = state.salary.toCurrency(),
                    onValueChange = {  },
                    enabled = false,
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 12.dp)
        )
    }
}