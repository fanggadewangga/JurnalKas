package com.ptotakkanan.jurnalkas.feature.wallet.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppDialog
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import com.ptotakkanan.jurnalkas.feature.common.util.ObserveAsEvents
import com.ptotakkanan.jurnalkas.feature.wallet.components.TransactionBox
import com.ptotakkanan.jurnalkas.feature.wallet.components.TransactionItem
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue60
import com.ptotakkanan.jurnalkas.theme.primary10
import com.ptotakkanan.jurnalkas.theme.primary20
import es.dmoral.toasty.Toasty

@Composable
fun WalletDetailScreen(
    navController: NavController,
    walletId: String,
    viewModel: WalletDetailViewModel = viewModel(),
) {

    LaunchedEffect(Unit) { viewModel.fetchWalletDetail(walletId) }

    val context = LocalContext.current
    val state by viewModel.state

    ObserveAsEvents(flow = viewModel.eventFlow) { event ->
        when (event) {
            is WalletDetailViewModel.UiEvent.ShowErrorMessage -> Toasty.error(
                context,
                event.message
            ).show()
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = blue60)
            .padding(vertical = 32.dp, horizontal = 24.dp)
    ) {
        AppButton(
            onClick = { /*TODO*/ },
            backgroundColor = primary20,
            modifier = Modifier
                .width(160.dp)
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
                    text = "Detail Dompet",
                    color = Color.White,
                    textStyle = Typography.bodyMedium()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = state.walletDetail?.wallet?.icon,
                    contentDescription = "Cash icon",
                    modifier = Modifier.size(16.dp)
                )
                AppText(
                    text = state.walletDetail?.wallet?.name ?: "",
                    color = primary20,
                    textStyle = Typography.titleMedium()
                        .copy(fontSize = 16.sp, fontWeight = FontWeight(700))
                )
            }

            Box(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    AsyncImage(
                        model = R.drawable.ic_filter,
                        contentDescription = "Filter icon",
                        modifier = Modifier.size(16.dp)
                    )
                    AppText(
                        text = "Rentang Waktu",
                        color = primary20,
                        textStyle = Typography.titleMedium()
                            .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                    )
                }
            }
        }

        // Transaction Summary Box
        TransactionBox(
            income = state.walletDetail?.income ?: 0,
            outcome = state.walletDetail?.outcome ?: 0,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    navController.navigate(
                        Screen.Analysis.route.replace(
                            "{walletId}",
                            walletId
                        )
                    )
                }
        )


        // Transactions
        LazyColumn {
            val groupedTransactions = state.walletDetail?.transactions?.groupBy { it.date }
            groupedTransactions?.forEach { (date, transaction) ->
                item {
                    AppText(
                        text = date,
                        color = primary20,
                        textStyle = Typography.titleMedium()
                            .copy(fontSize = 12.sp, fontWeight = FontWeight(600))
                    )
                }
                items(transaction) { item ->
                    TransactionItem(
                        transaction = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}