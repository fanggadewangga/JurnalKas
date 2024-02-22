package com.ptotakkanan.jurnalkas.feature.input.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ptotakkanan.jurnalkas.feature.input.InputEvent
import com.ptotakkanan.jurnalkas.feature.input.InputState
import com.ptotakkanan.jurnalkas.feature.input.InputViewModel
import com.ptotakkanan.jurnalkas.feature.input.components.WalletItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WalletOptionScreen(
    viewModel: InputViewModel,
    state: InputState,
) {
    Spacer(modifier = Modifier.height(32.dp))
    FlowRow(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        maxItemsInEachRow = 3,
        modifier = Modifier.fillMaxWidth()
    ) {
        state.wallet.forEach { wallet ->
            WalletItem(
                wallet = wallet.option,
                selected = wallet.selected,
                modifier = Modifier
                    .size(90.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = Color.White),
                        onClick = { viewModel.onEvent(InputEvent.ChooseWalletCategory(wallet)) }
                    )
            )
        }
    }
}