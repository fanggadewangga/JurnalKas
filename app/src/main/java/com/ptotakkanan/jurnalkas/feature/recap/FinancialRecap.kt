package com.ptotakkanan.jurnalkas.feature.recap

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.recap.components.RecapItem
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue60
import com.ptotakkanan.jurnalkas.theme.green20
import com.ptotakkanan.jurnalkas.theme.primary20

@Composable
fun FinancialRecapScreen(
    navController: NavController,
    viewModel: FinancialRecapViewModel = viewModel(),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = blue60)
            .padding(vertical = 32.dp)
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

        Spacer(modifier = Modifier.height(32.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE9E9E9), shape = RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
        ) {
            AppText(
                text = "21 Februari - 07 Maret 2023",
                textStyle = Typography.titleMedium().copy(fontSize = 12.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        AppText(
            text = "Total",
            textStyle = Typography.titleMedium().copy(fontSize = 18.sp),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        RecapItem(
            title = "Pengeluaran",
            nominal = 718000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        RecapItem(
            title = "Pemasukan",
            nominal = 18000000,
            titleColor = green20,
            nominalColor = green20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Divider(thickness = 1.5.dp, modifier = Modifier.fillMaxWidth())
        RecapItem(
            title = "Selisih",
            nominal = 1082000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        )
        Divider(thickness = 1.5.dp, modifier = Modifier.fillMaxWidth())

        // Outcome Category
        Spacer(modifier = Modifier.height(8.dp))
        AppText(
            text = "Kategori Pengeluaran",
            textStyle = Typography.titleMedium().copy(fontSize = 18.sp),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp)
        )
        RecapItem(
            title = "Hadiah",
            nominal = 235000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        RecapItem(
            title = "Pendidikan",
            nominal = 650000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        RecapItem(
            title = "Makan",
            nominal = 117000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        RecapItem(
            title = "Jajan",
            nominal = 26000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        RecapItem(
            title = "Lalu Lintas",
            nominal = 25000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        RecapItem(
            title = "Harian",
            nominal = 200000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        Divider(thickness = 1.5.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp))

        // Income Category
        AppText(
            text = "Kategori Pemasukan",
            textStyle = Typography.titleMedium().copy(fontSize = 18.sp),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        RecapItem(
            title = "Gaji",
            nominal = 1500000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        RecapItem(
            title = "Part Time",
            nominal = 200000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        RecapItem(
            title = "Bonus",
            nominal = 100000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}