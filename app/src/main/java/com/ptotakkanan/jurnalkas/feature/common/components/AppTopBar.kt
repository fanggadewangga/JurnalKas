package com.ptotakkanan.jurnalkas.feature.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.ptotakkanan.jurnalkas.theme.Typography

@Composable
fun AppTopBar(title: String, onBackPressed: () -> Unit) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier
                            .clickable {
                                onBackPressed()
                            }
                    )
                    AppText(text = title, textStyle = Typography.titleMedium())
                }
            }
        }
    }
}