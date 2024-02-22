package com.ptotakkanan.jurnalkas.feature.common.navigation

import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.route.Screen

sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
) {
    data object Wallet: BottomNavigationItem(route = Screen.Wallet.route, icon = R.drawable.ic_wallet)
    data object Calendar: BottomNavigationItem(route = Screen.Calendar.route, icon = R.drawable.ic_home)
    data object Input: BottomNavigationItem(route = Screen.Input.route, icon = R.drawable.ic_graph)
}