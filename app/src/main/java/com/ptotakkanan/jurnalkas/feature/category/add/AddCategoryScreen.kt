package com.ptotakkanan.jurnalkas.feature.category.add

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue50
import com.ptotakkanan.jurnalkas.theme.primary10
import com.ptotakkanan.jurnalkas.theme.primary20
import com.ptotakkanan.jurnalkas.theme.secondary10

@Composable
fun AddCategoryScreen(
    navController: NavController,
    viewModel: AddCategoryViewModel = viewModel(),
) {

    val state by viewModel.state

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = blue50)
            .padding(start = 24.dp, top = 24.dp, end = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppButton(
            onClick = { /*TODO*/ },
            backgroundColor = primary20,
            modifier = Modifier
                .width(128.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                AsyncImage(
                    model = R.drawable.ic_setting,
                    contentDescription = "Setting icon",
                    modifier = Modifier.size(18.dp)
                )
                AppText(
                    text = "Pengaturan",
                    color = Color.White,
                    textStyle = Typography.bodyMedium()
                        .copy(fontSize = 12.sp, fontWeight = FontWeight(700))
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        AppText(
            text = "Kategori",
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                fontSize = 22.sp
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(24.dp)
            ) {
                AppText(
                    text = "Nama Kategori",
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway)),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500)
                    ),
                    color = Color(0xFF003566)
                )
                Spacer(modifier = Modifier.height(6.dp))
                BasicTextField(
                    value = state.name,
                    onValueChange = { viewModel.onEvent(AddCategoryEvent.EnterCategory(it)) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(12.dp))
                AppText(
                    text = "Deskripsi",
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway)),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500)
                    ),
                    color = Color(0xFF003566)
                )
                Spacer(modifier = Modifier.height(6.dp))
                BasicTextField(
                    value = state.description,
                    onValueChange = { viewModel.onEvent(AddCategoryEvent.EnterDescription(it)) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        fontSize = 12.sp,
                        color = Color(0xFF003566)
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
            }


            LazyRow(modifier = Modifier.padding(horizontal = 24.dp)) {
                items(viewModel.iconOption) { item ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp),
                        border = if (item.selected) BorderStroke(
                            width = 2.dp,
                            color = primary10
                        ) else null,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(end = 8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(color = Color.White),
                                onClick = { viewModel.onEvent(AddCategoryEvent.ChooseCategory(item)) }
                            )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AsyncImage(
                                model = item.option,
                                contentDescription = "Icon",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                AppButton(
                    onClick = {

                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .size(40.dp),
                ) {
                    AsyncImage(
                        model = R.drawable.ic_check,
                        contentDescription = "Check Icon",
                        colorFilter = ColorFilter.tint(color = secondary10)
                    )
                }
            }
        }
    }
}