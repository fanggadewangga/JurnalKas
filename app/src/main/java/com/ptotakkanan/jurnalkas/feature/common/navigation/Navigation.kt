package com.ptotakkanan.jurnalkas.feature.common.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ptotakkanan.jurnalkas.feature.category.CategoryDetailScreen
import com.ptotakkanan.jurnalkas.feature.category.CategoryScreen
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import com.ptotakkanan.jurnalkas.feature.login.LoginScreen
import com.ptotakkanan.jurnalkas.feature.note.NoteScreen
import com.ptotakkanan.jurnalkas.feature.recap.FinancialRecapScreen
import com.ptotakkanan.jurnalkas.feature.register.RegisterScreen
import com.ptotakkanan.jurnalkas.feature.wallet.WalletDetailScreen
import com.ptotakkanan.jurnalkas.feature.wallet.WalletScreen
import com.ptotakkanan.jurnalkas.feature.welcome.WelcomeScreen


@SuppressLint("NewApi")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    NavHost(navController = navController, startDestination = Screen.FinancialRecap.route) {

        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController, screenWidth = screenWidth)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController, screenWidth = screenWidth)
        }
        
        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }
        
        composable(route = Screen.Note.route) {
            NoteScreen(navController = navController)
        }

        composable(route = Screen.CategoryDetail.route) {
            CategoryDetailScreen(navController = navController)
        }

        composable(route = Screen.Wallet.route) {
            WalletScreen(navController = navController, screenWidth = screenWidth)
        }

        composable(route = Screen.WalletDetail.route) {
            WalletDetailScreen(navController = navController)
        }

        composable(route = Screen.FinancialRecap.route) {
            FinancialRecapScreen(navController = navController)
        }
    }
}