package com.ptotakkanan.jurnalkas.feature.tax.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.tax.TaxEvent
import com.ptotakkanan.jurnalkas.feature.tax.TaxState
import com.ptotakkanan.jurnalkas.feature.tax.TaxViewModel
import com.ptotakkanan.jurnalkas.feature.tax.components.TaxInputItem
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary20
import com.ptotakkanan.jurnalkas.theme.secondary10

@Composable
fun TaxCounterScreen(
    navController: NavController,
    viewModel: TaxViewModel,
    state: TaxState,
) {
    Spacer(modifier = Modifier.height(24.dp))
    AppText(
        text = "Hitung Pajakmu",
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.raleway_bold)),
            fontSize = 24.sp
        )
    )
    Spacer(modifier = Modifier.height(8.dp))
    AppText(
        text = "Masukkan data- data yang di perlukan",
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.raleway_bold)),
            fontSize = 12.sp
        )
    )

    Spacer(modifier = Modifier.height(48.dp))
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TaxInputItem(
            title = "NIK",
            content = {
                BasicTextField(
                    value = state.nik,
                    onValueChange = { viewModel.onEvent(TaxEvent.EnterNik(it)) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    )
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 12.dp)
        )
        TaxInputItem(
            title = "Punya NPWP?",
            content = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    viewModel.npwpOption.forEach { item ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        ) {
                            RadioButton(
                                selected = item.selected,
                                onClick = { viewModel.onEvent(TaxEvent.ChooseNPWP(item)) },
                                colors = RadioButtonDefaults.colors(selectedColor = primary20)
                            )
                            AppText(
                                text = item.option,
                                textStyle = Typography.bodyMedium().copy(fontSize = 12.sp),
                                color = primary20
                            )
                        }
                    }
                }
            },
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 12.dp)
        )
        TaxInputItem(
            title = "Status Perkawinan/Tanggungan",
            content = {
                BasicTextField(
                    value = state.nik,
                    onValueChange = { viewModel.onEvent(TaxEvent.EnterNik(it)) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    )
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 12.dp)
        )
        TaxInputItem(
            title = "Gaji Perbulan",
            content = {
                BasicTextField(
                    value = state.nik,
                    onValueChange = { viewModel.onEvent(TaxEvent.EnterNik(it)) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    )
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 12.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            AppButton(
                onClick = {
                    viewModel.onEvent(TaxEvent.ShowResult(true))
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .size(40.dp),
            ) {
                AsyncImage(
                    model = R.drawable.ic_check,
                    contentDescription = "Check Icon",
                    colorFilter = ColorFilter.tint(color = secondary10)
                )
            }
        }
    }
}