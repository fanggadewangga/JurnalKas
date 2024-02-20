package com.ptotakkanan.jurnalkas.feature.wallet.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.core.ext.toCurrency
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.green20
import com.ptotakkanan.jurnalkas.theme.primary20

@Composable
fun TransactionBox(
    modifier: Modifier = Modifier,
    income: Long,
    outcome: Long,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            // Income
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = R.drawable.ic_arrow_income,
                        contentDescription = "Arrow income",
                        modifier = Modifier.size(16.dp)
                    )
                    AppText(
                        text = "Pemasukan",
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.raleway)),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600)
                        ),
                        color = Color.Gray
                    )
                }
                AppText(
                    text = income.toCurrency(),
                    textStyle = Typography
                        .titleMedium()
                        .copy(fontSize = 14.sp, fontWeight = FontWeight(600)),
                    color = primary20
                )
            }

            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .height(100.dp)
                    .width(1.dp)
            )

            // Outcome
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = R.drawable.ic_arrow_outcome,
                        contentDescription = "Arrow outcome",
                        modifier = Modifier.size(16.dp)
                    )
                    AppText(
                        text = "Pengeluaran",
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.raleway)),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600)
                        ),
                        color = Color.Gray
                    )
                }
                AppText(
                    text = outcome.toCurrency(),
                    textStyle = Typography
                        .titleMedium()
                        .copy(fontSize = 14.sp, fontWeight = FontWeight(600)),
                    color = primary20
                )
            }
        }

        Divider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.fillMaxWidth())

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            AppText(
                text = "Selisih ",
                textStyle = Typography
                    .titleMedium()
                    .copy(fontSize = 14.sp, fontWeight = FontWeight(600)),
                color = primary20
            )
            AppText(
                text = (income - outcome).toCurrency(),
                textStyle = Typography
                    .titleMedium()
                    .copy(fontSize = 14.sp, fontWeight = FontWeight(600)),
                color = if (income - outcome > 0) green20 else Color.Red
            )
        }
    }
}