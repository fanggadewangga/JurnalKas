package com.ptotakkanan.jurnalkas.feature.common.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ptotakkanan.jurnalkas.feature.category.CategoryScreen
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import com.ptotakkanan.jurnalkas.feature.login.LoginScreen
import com.ptotakkanan.jurnalkas.feature.register.RegisterScreen
import com.ptotakkanan.jurnalkas.feature.welcome.WelcomeScreen


@SuppressLint("NewApi")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController, screenWidth = screenWidth)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        
        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }
    }
}