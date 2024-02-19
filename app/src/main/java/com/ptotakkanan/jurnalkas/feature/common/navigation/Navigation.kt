package com.ptotakkanan.jurnalkas.feature.common.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import com.ptotakkanan.jurnalkas.feature.welcome.WelcomeScreen


@SuppressLint("NewApi")
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {

        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
    }
}