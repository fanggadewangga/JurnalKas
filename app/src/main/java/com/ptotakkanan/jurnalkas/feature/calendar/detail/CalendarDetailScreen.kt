package com.ptotakkanan.jurnalkas.feature.calendar.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.calendar.components.CalendarTransactionItem
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary20
import com.ptotakkanan.jurnalkas.theme.secondary0

@Composable
fun CalendarDetailScreen(
    navController: NavController,
    viewModel: CalendarDetailViewModel = viewModel(),
    screenWidth: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = R.drawable.ic_arrow_left,
                contentDescription = "Arrow back",
                colorFilter = ColorFilter.tint(primary20),
                modifier = Modifier.size(24.dp)
            )
            AppText(
                text = "Detail Kalender",
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700)
                ),
                color = primary20
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Options
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = secondary0),
                elevation = CardDefaults.cardElevation(if (viewModel.options[0].selected) 8.dp else 0.dp),
                modifier = Modifier
                    .width((screenWidth * 0.37).dp)
                    .height(56.dp)
                    .clickable {
                        viewModel.onEvent(CalendarDetailEvent.SelectCategory(viewModel.options[0]))
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppText(
                        text = "Pemasukan",
                        textStyle = Typography.titleMedium().copy(fontSize = 16.sp),
                        color = if (viewModel.options[0].selected) Color.White else Color.Gray,
                    )
                }

            }
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = secondary0),
                elevation = CardDefaults.cardElevation(if (viewModel.options[0].selected) 8.dp else 0.dp),
                modifier = Modifier
                    .width((screenWidth * 0.37).dp)
                    .height(56.dp)
                    .clickable {
                        viewModel.onEvent(CalendarDetailEvent.SelectCategory(viewModel.options[1]))
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppText(
                        text = "Pengeluaran",
                        textStyle = Typography.titleMedium().copy(fontSize = 16.sp),
                        color = if (viewModel.options[1].selected) Color.White else Color.Gray,
                    )
                }
            }
        }

        // Transaction Item
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn {
            val items = if (viewModel.options.first().selected)
                viewModel.dummyIncome
            else
                viewModel.dummyOutcome
            items(items) { item ->
                CalendarTransactionItem(calendarTransaction = item)
            }
        }
    }
}