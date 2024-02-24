package com.ptotakkanan.jurnalkas.feature.category.page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ptotakkanan.jurnalkas.feature.category.categories.CategoryViewModel
import com.ptotakkanan.jurnalkas.feature.category.components.MoreCategoryItem
import com.ptotakkanan.jurnalkas.feature.common.route.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun SecondCategoryScreen(
    navController: NavController,
    pagerState: PagerState,
    viewModel: CategoryViewModel,
) {
    val scope = rememberCoroutineScope()

    BackHandler {
        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
    }

    Spacer(modifier = Modifier.height(48.dp))
    FlowRow(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        maxItemsInEachRow = 3,
        modifier = Modifier.fillMaxWidth()
    ) {
        viewModel.moreCategory.forEach { category ->
            MoreCategoryItem(
                name = category.option.name,
                icon = category.option.image,
                modifier = Modifier
                    .size(90.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = Color.White),
                        onClick = {
                            when (category.option.name) {
                                "Profil" -> navController.navigate(Screen.Profile.route)
                                "Book" -> {}
                                "Dompet" -> navController
                                    .navigate(Screen.Wallet.route) {
                                        popUpTo(Screen.Category.route) {
                                            inclusive = true
                                        }
                                    }
                                "Cari" -> navController.navigate(Screen.Note.route)
                                "Pengingat" -> {}
                                "Kategori" -> scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                                "Tentang" -> {}
                                "Google Drive" -> {}
                                "Premium" -> {}
                            }
                        }
                    )
            )
        }
    }
}