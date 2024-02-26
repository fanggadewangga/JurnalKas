package com.ptotakkanan.jurnalkas.feature.common.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ptotakkanan.jurnalkas.feature.analysis.AnalysisScreen
import com.ptotakkanan.jurnalkas.feature.calendar.calendar.CalendarScreen
import com.ptotakkanan.jurnalkas.feature.calendar.detail.CalendarDetailScreen
import com.ptotakkanan.jurnalkas.feature.category.add.AddCategoryScreen
import com.ptotakkanan.jurnalkas.feature.category.categories.CategoryScreen
import com.ptotakkanan.jurnalkas.feature.category.detail.CategoryDetailScreen
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import com.ptotakkanan.jurnalkas.feature.input.InputScreen
import com.ptotakkanan.jurnalkas.feature.login.LoginScreen
import com.ptotakkanan.jurnalkas.feature.note.NoteScreen
import com.ptotakkanan.jurnalkas.feature.profile.ProfileScreen
import com.ptotakkanan.jurnalkas.feature.register.RegisterScreen
import com.ptotakkanan.jurnalkas.feature.tax.TaxScreen
import com.ptotakkanan.jurnalkas.feature.wallet.detail.WalletDetailScreen
import com.ptotakkanan.jurnalkas.feature.wallet.wallet.WalletScreen
import com.ptotakkanan.jurnalkas.feature.welcome.WelcomeScreen


@SuppressLint("NewApi")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {

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

        composable(route = Screen.CategoryDetail.route) { navBackStackEntry ->
            val categoryId = navBackStackEntry.arguments?.getString("categoryId")
            categoryId?.let { CategoryDetailScreen(categoryId = it) }
        }

        composable(route = Screen.Wallet.route) {
            WalletScreen(navController = navController, screenWidth = screenWidth)
        }

        composable(route = Screen.WalletDetail.route) { navBackStackEntry ->
            val walletId = navBackStackEntry.arguments?.getString("walletId")
            walletId?.let { WalletDetailScreen(navController = navController, walletId = it, screenHeight = screenHeight) }
        }
        
        composable(route = Screen.Analysis.route) {navBackStackEntry ->
            val walletId = navBackStackEntry.arguments?.getString("walletId")
            walletId?.let { AnalysisScreen(walletId = it, navController = navController) }
        }
        
        composable(route = Screen.CalendarDetail.route) {
            CalendarDetailScreen(navController = navController, screenWidth = screenWidth)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        
        composable(route = Screen.Tax.route) {
            TaxScreen(navController = navController)
        }

        composable(route = Screen.Input.route) {
            InputScreen(navController = navController)
        }

        composable(route = Screen.AddCategory.route) {
            AddCategoryScreen(navController = navController)
        }

        composable(route = Screen.Calendar.route) {
            CalendarScreen(navController = navController)
        }
    }
}