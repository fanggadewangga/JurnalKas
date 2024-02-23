package com.ptotakkanan.jurnalkas.feature.calendar

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.core.ext.toCurrency
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.common.navigation.BottomNavigationBar
import com.ptotakkanan.jurnalkas.feature.input.components.TabOptionItem
import com.ptotakkanan.jurnalkas.feature.wallet.components.TransactionItem
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue50
import com.ptotakkanan.jurnalkas.theme.primary20

@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: CalendarViewModel = viewModel(),
) {

    val state by viewModel.state

    Scaffold(
        bottomBar = {
            BottomAppBar(
                tonalElevation = 8.dp,
                containerColor = Color.White,
            ) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { it ->
        val bottomPadding = it.calculateBottomPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding)
                .background(color = primary20)
                .padding(top = 32.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    TabOptionItem(
                        title = viewModel.tabOptions.first().option,
                        icon = R.drawable.ic_calendar,
                        selected = viewModel.tabOptions.first().selected,
                        backgroundColor = if (viewModel.tabOptions.first().selected) Color.White else Color.Transparent,
                        shape = RoundedCornerShape(12.dp),
                        selectedTint = primary20,
                        unselectedTint = Color.White,
                        onSelect = {
                            viewModel.tabOptions.apply {
                                first().selected = true
                                last().selected = false
                            }
                        }
                    )
                    TabOptionItem(
                        title = viewModel.tabOptions.last().option,
                        icon = if (viewModel.tabOptions.last().selected) R.drawable.ic_detail_selected else R.drawable.ic_detail_unselected,
                        selected = viewModel.tabOptions.last().selected,
                        backgroundColor = if (viewModel.tabOptions.last().selected) Color.White else Color.Transparent,
                        shape = RoundedCornerShape(12.dp),
                        selectedTint = primary20,
                        unselectedTint = Color.White,
                        onSelect = {
                            viewModel.tabOptions.apply {
                                first().selected = false
                                last().selected = true
                            }
                        },
                    )
                }
                AsyncImage(
                    model = R.drawable.ic_setting,
                    contentDescription = "Setting icon",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                Column {
                    AppText(
                        text = "Total Uang",
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            fontSize = 16.sp,
                        ),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AppText(
                        text = 14160000L.toCurrency(),
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 24.sp,
                        ),
                        color = Color.White
                    )
                }

                AsyncImage(
                    model = R.drawable.ic_dollar_2,
                    contentDescription = "Dollar icon",
                    modifier = Modifier.padding(end = 32.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_dollar_1,
                    contentDescription = "Dollar icon",
                    modifier = Modifier
                        .padding(end = 32.dp)
                        .align(Alignment.CenterVertically)
                )
                AsyncImage(
                    model = R.drawable.iv_user_calendar,
                    contentDescription = "Dollar icon",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.Bottom)
                )
            }

            Card(
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                colors = CardDefaults.cardColors(containerColor = blue50),
                modifier = Modifier.fillMaxHeight()
            ) {

                // Calendar
                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.padding(24.dp)
                ) {
                    AndroidView(
                        factory = {
                            CalendarView(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

               /* val groupedTransactions = viewModel.dummyTransaction.groupBy { it.date }
                groupedTransactions.forEach { (date, transaction) ->
                    AppText(
                        text = date,
                        color = primary20,
                        textStyle = Typography.titleMedium()
                            .copy(fontSize = 12.sp, fontWeight = FontWeight(600)),
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    transaction.forEach { item ->
                        TransactionItem(
                            transaction = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp, horizontal = 24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }*/
            }
        }
    }


}