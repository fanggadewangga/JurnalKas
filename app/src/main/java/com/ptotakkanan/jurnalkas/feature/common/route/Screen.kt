package com.ptotakkanan.jurnalkas.feature.common.route

sealed class Screen(val route: String) {
    data object Welcome: Screen("welcome")
    data object Login: Screen("login")
    data object Register: Screen("register")
    data object Category: Screen("category")
    data object Home: Screen("home")
    data object Wallet: Screen("wallet")
    data object Graph: Screen("graph")
    data object Note: Screen("note")
    data object CategoryDetail: Screen("category_detail")
    data object WalletDetail: Screen("wallet_detail")
    data object FinancialRecap: Screen("financial_recap")
    data object Analysis: Screen("analysis")
    data object Calendar: Screen("calendar")
    data object Profile: Screen("profile")
    data object TaxScreen: Screen("tax")

    fun withArgs(vararg args: String) = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}