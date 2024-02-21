package com.ptotakkanan.jurnalkas.feature.calendar.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ptotakkanan.jurnalkas.core.ext.toCurrency
import com.ptotakkanan.jurnalkas.domain.CalendarTransaction
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.green20
import com.ptotakkanan.jurnalkas.theme.primary20
import com.ptotakkanan.jurnalkas.theme.secondary10

@Composable
fun CalendarTransactionItem(
    modifier: Modifier = Modifier,
    calendarTransaction: CalendarTransaction,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = primary20),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            AppText(
                text = calendarTransaction.month,
                textStyle = Typography.titleMedium().copy(fontSize = 16.sp),
                color = Color.White
            )
            AppText(
                text = calendarTransaction.date,
                textStyle = Typography.titleMedium().copy(fontSize = 12.sp),
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AppText(
                    text = "Status",
                    textStyle = Typography.titleSmall().copy(fontSize = 12.sp),
                    color = Color.White
                )
                AppText(
                    text = if (calendarTransaction.isSuccess) "Sukses" else "Gagal",
                    textStyle = Typography.titleSmall().copy(fontSize = 12.sp),
                    color = if (calendarTransaction.isSuccess) green20 else Color.Red
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AppText(
                    text = "Amount",
                    textStyle = Typography.titleSmall().copy(fontSize = 12.sp),
                    color = Color.Gray
                )
                AppText(
                    text = calendarTransaction.nominal.toCurrency(),
                    textStyle = Typography.titleSmall().copy(fontSize = 12.sp),
                    color = secondary10
                )
            }
        }
    }
}