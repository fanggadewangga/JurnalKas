package com.ptotakkanan.jurnalkas.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import com.ptotakkanan.jurnalkas.feature.common.util.ObserveAsEvents
import com.ptotakkanan.jurnalkas.feature.profile.page.EditableProfileScreen
import com.ptotakkanan.jurnalkas.feature.profile.page.ViewOnlyProfileScreen
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue50
import com.ptotakkanan.jurnalkas.theme.primary20

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = viewModel()) {

    val state by viewModel.state

    ObserveAsEvents(flow = viewModel.eventFlow) { event ->
        when (event) {
            is ProfileViewModel.UiEvent.NavigateToLogin -> navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Profile.route) {
                    inclusive = true
                }
            }

            is ProfileViewModel.UiEvent.ShowErrorMessage -> TODO()
            ProfileViewModel.UiEvent.SwitchToEditable -> TODO()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = blue50)
            .padding(start = 24.dp, top = 24.dp, end = 24.dp)
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
                    model = R.drawable.ic_setting,
                    contentDescription = "Setting icon",
                    modifier = Modifier.size(18.dp)
                )
                AppText(
                    text = "Pengaturan",
                    color = Color.White,
                    textStyle = Typography.bodyMedium()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
            }
        }

        if (state.isEditable)
            EditableProfileScreen(viewModel = viewModel, state = state)
        else
            ViewOnlyProfileScreen(viewModel = viewModel, state = state)
    }
}