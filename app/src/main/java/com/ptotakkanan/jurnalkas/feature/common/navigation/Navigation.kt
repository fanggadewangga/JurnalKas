package com.ptotakkanan.jurnalkas.feature.common.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ptotakkanan.jurnalkas.feature.analysis.AnalysisScreen
import com.ptotakkanan.jurnalkas.feature.calendar.CalendarScreen
import com.ptotakkanan.jurnalkas.feature.category.CategoryDetailScreen
import com.ptotakkanan.jurnalkas.feature.category.CategoryScreen
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import com.ptotakkanan.jurnalkas.feature.login.LoginScreen
import com.ptotakkanan.jurnalkas.feature.note.NoteScreen
import com.ptotakkanan.jurnalkas.feature.profile.ProfileScreen
import com.ptotakkanan.jurnalkas.feature.recap.FinancialRecapScreen
import com.ptotakkanan.jurnalkas.feature.register.RegisterScreen
import com.ptotakkanan.jurnalkas.feature.tax.TaxScreen
import com.ptotakkanan.jurnalkas.feature.wallet.WalletDetailScreen
import com.ptotakkanan.jurnalkas.feature.wallet.WalletScreen
import com.ptotakkanan.jurnalkas.feature.welcome.WelcomeScreen


@SuppressLint("NewApi")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    NavHost(navController = navController, startDestination = Screen.TaxScreen.route) {

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
        
        composable(route = Screen.Analysis.route) {
            AnalysisScreen(navController = navController)
        }
        
        composable(route = Screen.Calendar.route) {
            CalendarScreen(navController = navController, screenWidth = screenWidth)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        
        composable(route = Screen.TaxScreen.route) {
            TaxScreen(navController = navController)
        }
    }
}