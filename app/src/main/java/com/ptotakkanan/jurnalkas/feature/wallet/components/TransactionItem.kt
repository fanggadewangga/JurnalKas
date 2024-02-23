package com.ptotakkanan.jurnalkas.feature.wallet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.core.ext.toCurrency
import com.ptotakkanan.jurnalkas.domain.Transaction
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.green30

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    transaction: Transaction,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = transaction.imageUrl,
                    contentDescription = "Note icon",
                    modifier = Modifier.size(32.dp)
                )
                Column {
                    AppText(
                        text = transaction.title,
                        textStyle = Typography.titleMedium()
                            .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                    )
                    AppText(
                        text = transaction.description,
                        textStyle = Typography.titleMedium()
                            .copy(fontSize = 10.sp, fontWeight = FontWeight(700)),
                        color = Color.Gray
                    )
                }
            }
            AppText(
                text = if (transaction.isIncome) "+${transaction.nominal.toCurrency()}" else "-${transaction.nominal.toCurrency()}",
                color = if (transaction.isIncome) green30 else Color.Red,
                textStyle = Typography
                    .titleMedium()
                    .copy(fontSize = 12.sp, fontWeight = FontWeight(700)),
            )
        }
    }
}