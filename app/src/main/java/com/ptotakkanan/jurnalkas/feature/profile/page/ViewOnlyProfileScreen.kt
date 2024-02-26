package com.ptotakkanan.jurnalkas.feature.profile.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppDialog
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.profile.ProfileEvent
import com.ptotakkanan.jurnalkas.feature.profile.ProfileState
import com.ptotakkanan.jurnalkas.feature.profile.ProfileViewModel
import com.ptotakkanan.jurnalkas.feature.profile.components.ViewOnlyProfileItem
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue10
import com.ptotakkanan.jurnalkas.theme.primary10
import com.ptotakkanan.jurnalkas.theme.primary20

@Composable
fun ViewOnlyProfileScreen(
    viewModel: ProfileViewModel,
    state: ProfileState,
) {

    if (state.isLoading)
        AppDialog(
            dialogContent = { CircularProgressIndicator(color = primary10) },
            setShowDialog = {},
            onCancelClicked = {},
            onConfirmClicked = {},
            modifier = Modifier.size(120.dp)
        )

    Spacer(modifier = Modifier.height(32.dp))
    AppText(
        text = "Profile",
        color = Color(0xFF3A3A3A),
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.raleway_bold)),
            fontSize = 24.sp,
            fontWeight = FontWeight(700)
        )
    )
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = R.drawable.iv_default_avatar,
                contentDescription = "Avatar",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            AppText(
                text = "Edit Profil",
                textStyle = Typography.bodyMedium().copy(fontSize = 12.sp),
                color = blue10,
                modifier = Modifier.clickable {
                    viewModel.onEvent(ProfileEvent.SwitchToEditable(true))
                }
            )
        }
        Column {
            AppText(
                text = state.name,
                textStyle = Typography.titleMedium().copy(fontSize = 16.sp)
            )
            AppText(
                text = state.description,
                textStyle = Typography.bodyMedium().copy(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

    }
    Spacer(modifier = Modifier.height(32.dp))
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        AppText(
            text = "Informasi Pribadi",
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                fontSize = 16.sp,
                fontWeight = FontWeight(700)
            ),
            color = primary20,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        ViewOnlyProfileItem(
            title = "Alamat Email",
            value = state.email,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 16.dp, end = 24.dp)
        )
        ViewOnlyProfileItem(
            title = "Nama Pengguna",
            value = state.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 8.dp, end = 24.dp)
        )
        ViewOnlyProfileItem(
            title = "Jenis Kelamin",
            value = state.gender,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 8.dp, end = 24.dp)
        )
        ViewOnlyProfileItem(
            title = "Nomor Telepon",
            value = state.phone,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 8.dp, end = 24.dp)
        )
        ViewOnlyProfileItem(
            title = "NIK",
            value = state.nik,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 24.dp)
        )
        AsyncImage(
            model = R.drawable.ic_logout,
            contentDescription = "Logout",
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    viewModel.onEvent(ProfileEvent.Logout)
                }
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}