package com.ptotakkanan.jurnalkas.feature.common.navigation

import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.route.Screen

sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
) {
    data object Wallet: BottomNavigationItem(route = Screen.Wallet.route, icon = R.drawable.ic_wallet)
    data object Home: BottomNavigationItem(route = Screen.Home.route, icon = R.drawable.ic_home)
    data object Graph: BottomNavigationItem(route = Screen.Graph.route, icon = R.drawable.ic_graph)
}