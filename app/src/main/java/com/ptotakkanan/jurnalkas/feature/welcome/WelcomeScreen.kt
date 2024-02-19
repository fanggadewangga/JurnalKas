package com.ptotakkanan.jurnalkas.feature.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary10
import com.ptotakkanan.jurnalkas.theme.primary20

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = R.drawable.iv_ellipse_1,
            contentDescription = "Ellips",
            modifier = Modifier.align(
                Alignment.TopStart
            )
        )
        AsyncImage(
            model = R.drawable.iv_ellipse_2,
            contentDescription = "Ellips",
            modifier = Modifier.align(
                Alignment.BottomEnd
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                model = R.drawable.iv_welcome,
                contentDescription = "Welcome image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(48.dp))
            AppText(
                text = "Hai, Selamat datang \nAtur Keuangan Anda Yuk!",
                textStyle = Typography.titleLarge().copy(fontSize = 22.sp),
                modifier = Modifier.padding(start = 16.dp, top = 12.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
        ) {
            AppButton(
                backgroundColor = primary20,
                onClick = { navController.navigate(Screen.Login.route) },
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .width(140.dp)
                    .height(38.dp)
            ) {
                AppText(text = "Masuk", color = Color.White)
            }
            AppButton(
                backgroundColor = Color.White,
                onClick = { navController.navigate(Screen.Register.route) },
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .width(140.dp)
                    .height(38.dp)
            ) {
                AppText(text = "Daftar", color = primary20)
            }
        }
    }
}