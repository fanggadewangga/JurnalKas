package com.ptotakkanan.jurnalkas.feature.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.core.ext.toCurrency
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.common.navigation.BottomNavigationBar
import com.ptotakkanan.jurnalkas.feature.wallet.components.WalletItem
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue60
import com.ptotakkanan.jurnalkas.theme.green20
import com.ptotakkanan.jurnalkas.theme.primary20
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WalletScreen(
    navController: NavController,
    screenWidth: Int,
    viewModel: WalletViewModel = viewModel(),
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                shape = CircleShape,
                containerColor = Color.White,
                modifier = Modifier.size(56.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_add,
                    contentDescription = "Add icon",
                    colorFilter = ColorFilter.tint(primary20),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(color = blue60)
                .padding(start = 24.dp, top = 32.dp, end = 24.dp, bottom = bottomPadding + 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AppButton(
                onClick = { /*TODO*/ },
                backgroundColor = primary20,
                modifier = Modifier
                    .width(128.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    AsyncImage(
                        model = R.drawable.ic_wallet_main,
                        contentDescription = "Wallet icon",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.size(18.dp)
                    )
                    AppText(
                        text = "Dompet",
                        color = Color.White,
                        textStyle = Typography.bodyMedium()
                            .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                    )
                }
            }

            // Chart
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = primary20, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                    ) {
                        AppText(
                            text = "Detail Transaksi\nAnda",
                            textStyle = Typography
                                .titleMedium()
                                .copy(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(700),
                                    lineHeight = 14.sp
                                )
                        )
                        Box(
                            modifier = Modifier
                                .width(88.dp)
                                .background(
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(100.dp)
                                )
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                AppText(
                                    text = "Month",
                                    color = Color.White,
                                    textStyle = Typography.titleSmall()
                                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                                )
                                AsyncImage(
                                    model = R.drawable.ic_arrow_down,
                                    contentDescription = "Arrow down",
                                    colorFilter = ColorFilter.tint(Color.White),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(120.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(100.dp))
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            AppText(
                                text = "June 2020",
                                color = green20,
                                textStyle = Typography.titleSmall().copy(fontSize = 12.sp)
                            )
                            AppText(
                                text = "+${35000000L.toCurrency()}",
                                textStyle = Typography.titleSmall().copy(fontSize = 12.sp)
                            )
                        }
                    }

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
                        modifier = Modifier.height(120.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(color = Color.Black, modifier = Modifier.width((screenWidth * 0.4).dp))
                AppText(
                    text = " detail ",
                    textStyle = Typography.bodySmall()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(600))
                )
                Divider(color = Color.Black, modifier = Modifier.width((screenWidth * 0.4).dp))
            }

            state.wallets.forEach { wallet ->
                WalletItem(
                    name = wallet.name,
                    nominal = wallet.balance,
                    imageUrl = wallet.icon,
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}