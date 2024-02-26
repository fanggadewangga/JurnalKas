package com.ptotakkanan.jurnalkas.feature.common.route

sealed class Screen(val route: String) {
    data object Welcome: Screen("welcome")
    data object Login: Screen("login")
    data object Register: Screen("register")
    data object Category: Screen("category")
    data object Wallet: Screen("wallet")
    data object Note: Screen("note")
    data object CategoryDetail: Screen("category_detail/{categoryId}")
    data object WalletDetail: Screen("wallet_detail/{walletId}")
    data object FinancialRecap: Screen("financial_recap")
    data object Analysis: Screen("analysis/{walletId}")
    data object CalendarDetail: Screen("calendar_detail")
    data object Profile: Screen("profile")
    data object Tax: Screen("tax")
    data object Input: Screen("input")
    data object AddCategory: Screen("add_category")
    data object Calendar: Screen("calendar")
    data object Backup: Screen("backup")

    fun withArgs(vararg args: String) = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}