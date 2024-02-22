package com.ptotakkanan.jurnalkas.feature.common.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ptotakkanan.jurnalkas.theme.primary0
import com.ptotakkanan.jurnalkas.theme.secondary0

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        BottomNavigationItem.Wallet,
        BottomNavigationItem.Calendar,
        BottomNavigationItem.Input,
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.LightGray,
        tonalElevation = 24.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = primary0,
                    selectedTextColor = primary0,
                    unselectedTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.White
                ),
                icon = {
                    if (currentRoute == item.route)
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            tint = secondary0,
                            modifier = Modifier.size(24.dp)
                        )
                    else
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            tint = primary0,
                            modifier = Modifier.size(24.dp)
                        )
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.id) {
                            saveState = true
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentRoute == item.route,
                enabled = currentRoute != item.route,
            )
        }
    }
}