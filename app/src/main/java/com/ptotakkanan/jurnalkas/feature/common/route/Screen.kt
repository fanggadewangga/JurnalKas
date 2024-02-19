package com.ptotakkanan.jurnalkas.feature.common.route

sealed class Screen(val route: String) {
    data object Welcome: Screen("welcome")
    data object Login: Screen("login")
    data object Register: Screen("register")
    data object Category: Screen("category")
    data object Home: Screen("home")
    data object Wallet: Screen("wallet")
    data object Graph: Screen("graph")
}