package com.ptotakkanan.jurnalkas.feature.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.core.ext.toCurrency
import com.ptotakkanan.jurnalkas.feature.analysis.components.AnalysisItem
import com.ptotakkanan.jurnalkas.feature.common.components.AppDialog
import com.ptotakkanan.jurnalkas.feature.common.components.AppSegmentationControl
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary10
import com.ptotakkanan.jurnalkas.theme.primary20
import com.ptotakkanan.jurnalkas.theme.secondary0

@Composable
fun AnalysisScreen(
    walletId: String,
    navController: NavController,
    viewModel: AnalysisViewModel = viewModel(),
) {

    val state by viewModel.state

    LaunchedEffect(Unit) {
        viewModel.apply {
            onEvent(AnalysisEvent.FetchIncome(walletId))
            onEvent(AnalysisEvent.FetchOutcome(walletId))
        }
    }

    if (state.isLoading)
        AppDialog(
            dialogContent = { CircularProgressIndicator(color = primary10) },
            setShowDialog = {},
            onCancelClicked = {},
            onConfirmClicked = {},
            modifier = Modifier.size(120.dp)
        )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(24.dp)) {
            AsyncImage(
                model = R.drawable.ic_arrow_left,
                contentDescription = "Arrow back",
                colorFilter = ColorFilter.tint(primary20),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            AppText(
                text = "Detail ${state.selectedTab}",
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700)
                ),
                color = primary20
            )
        }

        AppText(
            text = state.balance.toCurrency(),
            textStyle = Typography.titleMedium().copy(fontSize = 24.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        AppText(
            text = "Total Keuangan Anda",
            textStyle = Typography.titleSmall().copy(fontSize = 12.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp)
                    .height(120.dp)
                    .background(
                        color = primary20,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 72.dp, end = 24.dp)
                ) {
                    AppText(
                        text = "${state.selectedTab} bulan ini",
                        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.raleway_bold))),
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )
                    AppText(
                        text = if (state.selectedTab == viewModel.tabOptions.first()) state.thisMonthIncome.toCurrency() else state.thisMonthOutcome.toCurrency(),
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    LineChart(
                        linesChartData = listOf(
                            LineChartData(
                                points = listOf(
                                    LineChartData.Point(1f, ""),
                                    LineChartData.Point(1f, ""),
                                    LineChartData.Point(1f, ""),
                                ),
                                lineDrawer = SolidLineDrawer()
                            )
                        ),
                        animation = simpleChartAnimation(),
                        pointDrawer = FilledCircularPointDrawer(),
                        horizontalOffset = 5f,
                        modifier = Modifier
                            .height(120.dp)
                            .padding(horizontal = 16.dp)
                    )
                }

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 24.dp, top = 32.dp, end = 24.dp)
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    if (state.selectedTab == viewModel.tabOptions.first())
                        state.incomeTransactions.forEach { item ->
                            AnalysisItem(
                                transaction = item,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    else
                        state.outcomeTransactions.forEach { item ->
                            AnalysisItem(
                                transaction = item,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                }
            }
            AppSegmentationControl(
                items = viewModel.tabOptions,
                itemWidth = 140.dp,
                cornerRadius = 32,
                useFixedWidth = true,
                backgroundRadius = 16.dp,
                backgroundColor = secondary0,
                onItemSelection = {
                    viewModel.onEvent(AnalysisEvent.SwitchTab(viewModel.tabOptions[it]))
                },
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}