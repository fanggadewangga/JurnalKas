package com.ptotakkanan.jurnalkas.feature.input

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.input.components.TabOptionItem
import com.ptotakkanan.jurnalkas.feature.input.page.InputOutcomeScreen
import com.ptotakkanan.jurnalkas.feature.input.page.WalletOptionScreen
import com.ptotakkanan.jurnalkas.theme.blue50

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputScreen(
    navController: NavController,
    viewModel: InputViewModel = viewModel(),
) {

    val state by viewModel.state

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = blue50)
            .padding(start = 24.dp, top = 24.dp, end = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TabOptionItem(
                title = viewModel.tabOptions.first().option,
                icon = R.drawable.ic_wallet_main,
                selected = viewModel.tabOptions.first().selected,
                onSelect = {
                    viewModel.tabOptions.apply {
                        first().selected = true
                        last().selected = false
                    }
                }
            )
            TabOptionItem(
                title = viewModel.tabOptions.last().option,
                icon = R.drawable.ic_category,
                selected = viewModel.tabOptions.last().selected,
                onSelect = {
                    viewModel.tabOptions.apply {
                        first().selected = false
                        last().selected = true
                    }
                }
            )
        }

        if (viewModel.tabOptions.last().selected)
            InputOutcomeScreen(navController = navController, viewModel = viewModel, state = state)
        else
            WalletOptionScreen(viewModel = viewModel, state = state)
    }
}