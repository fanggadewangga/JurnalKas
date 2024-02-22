package com.ptotakkanan.jurnalkas.feature.tax

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
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
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.tax.components.TaxInputItem
import com.ptotakkanan.jurnalkas.feature.tax.page.TaxCounterScreen
import com.ptotakkanan.jurnalkas.feature.tax.page.TaxResultScreen
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue50
import com.ptotakkanan.jurnalkas.theme.primary20
import com.ptotakkanan.jurnalkas.theme.secondary10

@Composable
fun TaxScreen(
    navController: NavController,
    viewModel: TaxViewModel = viewModel(),
) {

    val state by viewModel.state

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

        if (state.isHasResult)
            TaxResultScreen(navController = navController, viewModel = viewModel, state = state)
        else
            TaxCounterScreen(navController = navController, viewModel = viewModel, state = state)
    }
}