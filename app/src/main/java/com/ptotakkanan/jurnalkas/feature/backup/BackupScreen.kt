package com.ptotakkanan.jurnalkas.feature.backup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue60
import com.ptotakkanan.jurnalkas.theme.primary20
import com.ptotakkanan.jurnalkas.theme.secondary10

@Composable
fun BackupScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 24.dp)
            .background(color = blue60)
    ) {
        AppButton(
            onClick = { /*TODO*/ },
            backgroundColor = secondary10,
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
                    colorFilter = ColorFilter.tint(color = primary20),
                    modifier = Modifier.size(18.dp)
                )
                AppText(
                    text = "Pengaturan",
                    color = primary20,
                    textStyle = Typography.bodyMedium()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        AppText(
            text = "Kategori",
            textStyle = Typography.titleMedium()
                .copy(fontSize = 24.sp, fontWeight = FontWeight(700)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(48.dp))

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = primary20),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_upload,
                    contentDescription = "Category icon",
                    modifier = Modifier.size(98.dp)
                )
                AppText(
                    text = "Cadangkan",
                    color = Color.White,
                    textStyle = Typography.titleLarge()
                        .copy(fontSize = 24.sp, fontWeight = FontWeight(700))
                )
            }
        }
        Spacer(modifier = Modifier.height(56.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = primary20),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 32.dp)
            ) {
                AppText(
                    text = "Ukuran Tercadangkan",
                    color = Color.White,
                    textStyle = Typography.bodySmall()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
                AppText(
                    text = "40 Mb",
                    color = Color.White,
                    textStyle = Typography.bodySmall()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
                Spacer(modifier = Modifier.height(24.dp))
                AppText(
                    text = "Pencadangan Terakhir",
                    color = Color.White,
                    textStyle = Typography.bodySmall()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
                AppText(
                    text = "Hari ini 10.54",
                    color = Color.White,
                    textStyle = Typography.bodySmall()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
                Spacer(modifier = Modifier.height(24.dp))
                AppText(
                    text = "Akun Google",
                    color = Color.White,
                    textStyle = Typography.bodySmall()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
                AppText(
                    text = "daudwakwaw@gmail.com",
                    color = Color.White,
                    textStyle = Typography.bodySmall()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
            }
        }
    }
}