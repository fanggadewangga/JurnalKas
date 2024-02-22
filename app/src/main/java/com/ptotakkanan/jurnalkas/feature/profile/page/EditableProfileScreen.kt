package com.ptotakkanan.jurnalkas.feature.profile.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.profile.ProfileEvent
import com.ptotakkanan.jurnalkas.feature.profile.ProfileState
import com.ptotakkanan.jurnalkas.feature.profile.ProfileViewModel
import com.ptotakkanan.jurnalkas.feature.profile.components.EditableProfileItem
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.secondary10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableProfileScreen(
    viewModel: ProfileViewModel,
    state: ProfileState
) {

    var expanded by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(24.dp))
    Box(contentAlignment = Alignment.Center) {
        AsyncImage(
            model = R.drawable.iv_default_avatar,
            contentDescription = "Avatar",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.BottomCenter)
        )
        AsyncImage(
            model = R.drawable.ic_edit,
            contentDescription = "Edit icon",
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(start = 6.dp, top = 8.dp, bottom = 4.dp)
                .size(20.dp)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    AppText(text = "Daud the Kid", textStyle = Typography.titleMedium().copy(fontSize = 16.sp))
    AppText(
        text = "Man who can’t be moved",
        textStyle = Typography.bodyMedium().copy(fontSize = 12.sp)
    )
    Spacer(modifier = Modifier.height(24.dp))
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        EditableProfileItem(
            title = "Alamat Email",
            content = {
                BasicTextField(
                    value = state.email,
                    onValueChange = { viewModel.onEvent(ProfileEvent.EnterEmail(it)) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    )
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 12.dp)
        )
        EditableProfileItem(
            title = "Nama Pengguna",
            content = {
                BasicTextField(
                    value = state.name,
                    onValueChange = { viewModel.onEvent(ProfileEvent.EnterName(it)) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    )
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 12.dp)
        )
        EditableProfileItem(
            title = "Kata Sandi",
            content = {
                BasicTextField(
                    value = state.password,
                    onValueChange = { viewModel.onEvent(ProfileEvent.EnterPassword(it)) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 12.dp)
        )
        EditableProfileItem(
            title = "Jenis Kelamin",
            content = {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    AppText(
                        text = state.gender,
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            fontSize = 12.sp,
                            color = Color(0xFF003566)
                        ),
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        DropdownMenuItem(text = {
                            AppText(
                                text = "Pria",
                                textStyle = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                                    fontSize = 12.sp,
                                    color = Color(0xFF003566)
                                ),
                            )
                        }, onClick = { expanded = !expanded }
                        )
                    }
                }
            },
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 12.dp)
        )
        EditableProfileItem(
            title = "Nomor Telepon",
            content = {
                BasicTextField(
                    value = state.phone,
                    onValueChange = { viewModel.onEvent(ProfileEvent.EnterPhone(it)) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    ),
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 12.dp)
        )
        EditableProfileItem(
            title = "NIK",
            content = {
                BasicTextField(
                    value = state.nik,
                    onValueChange = { viewModel.onEvent(ProfileEvent.EnterNik(it)) } ,
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    ),
                )
            },
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 12.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            AppButton(
                onClick = {
                    viewModel.onEvent(ProfileEvent.SwitchToEditable(false))
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .size(40.dp),
            ) {
                AsyncImage(
                    model = R.drawable.ic_check,
                    contentDescription = "Check Icon",
                    colorFilter = ColorFilter.tint(color = secondary10)
                )
            }
        }
    }
}