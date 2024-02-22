package com.ptotakkanan.jurnalkas.feature.category.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.category.CategoryEvent
import com.ptotakkanan.jurnalkas.feature.category.detail.CategoryViewModel
import com.ptotakkanan.jurnalkas.feature.category.components.CategoryItem
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary20
import kotlinx.coroutines.launch


@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun FirstCategoryScreen(
    pagerState: PagerState,
    viewModel: CategoryViewModel,
) {

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth()) {
        AppText(
            text = "Kategori",
            textStyle = Typography.titleMedium()
                .copy(fontSize = 24.sp, fontWeight = FontWeight(700)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(48.dp))
        FlowRow(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = 3,
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.category.forEach { category ->
                CategoryItem(
                    name = category.option.category,
                    icon = category.option.icon,
                    selected = category.selected,
                    modifier = Modifier
                        .size(90.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = Color.White),
                            onClick = { viewModel.onEvent(CategoryEvent.SelectCategory(category)) }
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .size(90.dp)
                .clickable { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }
                .align(Alignment.CenterHorizontally)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = R.drawable.ic_add,
                    contentDescription = "Add icon",
                    colorFilter = ColorFilter.tint(primary20),
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}