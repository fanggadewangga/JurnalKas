package com.ptotakkanan.jurnalkas.feature.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppBasicTextField
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppDialog
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import com.ptotakkanan.jurnalkas.feature.common.util.ObserveAsEvents
import com.ptotakkanan.jurnalkas.feature.login.LoginViewModel
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue20
import com.ptotakkanan.jurnalkas.theme.blue50
import com.ptotakkanan.jurnalkas.theme.primary10
import com.ptotakkanan.jurnalkas.theme.primary20
import es.dmoral.toasty.Toasty

@Composable
fun RegisterScreen(
    navController: NavController,
    screenWidth: Int,
    viewModel: RegisterViewModel = viewModel(),
) {

    val emailState by viewModel.emailState
    val passwordState by viewModel.passwordState
    val confirmPasswordState by viewModel.confirmPasswordState
    val isLoading by viewModel.isLoading
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.eventFlow) { event ->
        when (event) {
            is RegisterViewModel.UiEvent.ShowToast -> {
                Toasty.success(context, R.string.success_register).show()
            }

            is RegisterViewModel.UiEvent.NavigateToLogin -> {
                navController.navigate(Screen.Login.route)
            }
        }
    }

    if (isLoading)
        AppDialog(
            dialogContent = { CircularProgressIndicator(color = primary10) },
            setShowDialog = {},
            onCancelClicked = {},
            onConfirmClicked = {},
            modifier = Modifier.size(120.dp)
        )

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        AppText(
            text = "Daftar",
            textStyle = Typography
                .titleLarge()
                .copy(fontSize = 30.sp, fontWeight = FontWeight(700)),
            color = primary20
        )
        AppText(
            text = "Yuk daftar dulu disini, biar bisa\n" +
                    "nyobain fitur-fitur kami!",
            textStyle = Typography.headlineLarge()
                .copy(fontSize = 15.sp, lineHeight = 22.5.sp, fontWeight = FontWeight(600)),
            textAlign = TextAlign.Center

        )
        AppBasicTextField(
            placeHolder = "Email",
            value = emailState.text ?: "",
            onValueChange = { viewModel.onEvent(RegisterEvent.EnterEmail(it)) },
            shape = RoundedCornerShape(10.dp),
            borderWidth = 2.dp,
            borderColor = primary20,
            backgroundColor = blue50,
            textFieldHeight = 64.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        AppBasicTextField(
            placeHolder = "Password",
            value = passwordState.text ?: "",
            onValueChange = { viewModel.onEvent(RegisterEvent.EnterPassword(it))  },
            shape = RoundedCornerShape(10.dp),
            borderWidth = 2.dp,
            borderColor = primary20,
            textFieldHeight = 64.dp,
            backgroundColor = blue50,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        AppBasicTextField(
            placeHolder = "Konfirmasi Password",
            value = confirmPasswordState.text ?: "",
            onValueChange = { viewModel.onEvent(RegisterEvent.EnterConfirmPassword(it)) },
            shape = RoundedCornerShape(10.dp),
            borderWidth = 2.dp,
            borderColor = primary20,
            textFieldHeight = 64.dp,
            backgroundColor = blue50,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        AppButton(
            onClick = { viewModel.onEvent(RegisterEvent.Register) },
            backgroundColor = primary20,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(60.dp)
        ) {
            AppText(
                text = "Daftar",
                color = Color.White,
                textStyle = Typography.bodyMedium()
                    .copy(fontWeight = FontWeight(600), fontSize = 20.sp)
            )
        }
        AppText(
            text = "Sudah Punya Akun?",
            color = blue20,
            textStyle = Typography.bodyMedium()
                .copy(
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline
                ),
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 24.dp)
                .clickable { navController.navigate(Screen.Login.route) }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(color = Color.Black, modifier = Modifier.width((screenWidth * 0.4).dp))
            AppText(
                text = " atau ",
                textStyle = Typography.bodySmall()
                    .copy(fontSize = 12.sp, fontWeight = FontWeight(600))
            )
            Divider(color = Color.Black, modifier = Modifier.width((screenWidth * 0.4).dp))
        }
        AppButton(
            onClick = { viewModel.onEvent(RegisterEvent.Register) },
            backgroundColor = Color.White,
            borderColor = Color.Black,
            borderWidth = 1.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = R.drawable.ic_google,
                    contentDescription = "Google icon",
                    modifier = Modifier.size(36.dp)
                )
                AppText(
                    text = "Continue with Google",
                    textStyle = Typography.titleMedium()
                        .copy(fontSize = 18.sp, fontWeight = FontWeight(500))
                )
            }
        }
    }
}