package com.ptotakkanan.jurnalkas.feature.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary20
import com.ptotakkanan.jurnalkas.theme.secondary10

@Composable
fun AppSegmentationControl(
    items: List<String>,
    defaultSelectedItemIndex: Int = 0,
    useFixedWidth: Boolean = false,
    itemWidth: Dp = 180.dp,
    cornerRadius: Int = 10,
    selectedColor: Color = primary20,
    backgroundColor: Color = secondary10,
    backgroundRadius: Dp = 100.dp,
    onItemSelection: (selectedItemIndex: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = remember { mutableIntStateOf(defaultSelectedItemIndex) }

    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(backgroundRadius))
    ) {
        items.forEachIndexed { index, item ->
            OutlinedButton(
                modifier = when (index) {
                    0 -> {
                        if (useFixedWidth) {
                            Modifier
                                .height(64.dp)
                                .width(itemWidth)
                                .offset(0.dp, 0.dp)
                                .zIndex(if (selectedIndex.intValue == index) 1f else 0f)
                                .padding(8.dp)
                        } else {
                            Modifier
                                .wrapContentSize()
                                .offset(0.dp, 0.dp)
                                .zIndex(if (selectedIndex.intValue == index) 1f else 0f)
                        }
                    }

                    else -> {
                        if (useFixedWidth)
                            Modifier
                                .height(64.dp)
                                .width(itemWidth)
                                .offset(0.dp, 0.dp)
                                .zIndex(if (selectedIndex.intValue == index) 1f else 0f)
                                .padding(6.dp)
                        else
                            Modifier
                                .wrapContentSize()
                                .offset((-1 * index).dp, 0.dp)
                                .zIndex(if (selectedIndex.intValue == index) 1f else 0f)
                    }
                },
                onClick = {
                    selectedIndex.intValue = index
                    onItemSelection(selectedIndex.intValue)
                },
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStartPercent = cornerRadius,
                        topEndPercent = cornerRadius,
                        bottomStartPercent = cornerRadius,
                        bottomEndPercent = cornerRadius
                    )

                    items.size - 1 -> RoundedCornerShape(
                        topStartPercent = cornerRadius,
                        topEndPercent = cornerRadius,
                        bottomStartPercent = cornerRadius,
                        bottomEndPercent = cornerRadius
                    )

                    else -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = 0,
                        bottomStartPercent = 0,
                        bottomEndPercent = 0
                    )
                },
                contentPadding = PaddingValues(0.dp),
                border = BorderStroke(0.dp, Color.Transparent),
                colors = if (selectedIndex.intValue == index) {
                    ButtonDefaults.outlinedButtonColors(containerColor = selectedColor)
                } else {
                    ButtonDefaults.outlinedButtonColors(containerColor = backgroundColor)
                },
            ) {
                AppText(
                    text = item,
                    color = if (selectedIndex.intValue == index) secondary10 else Color.White,
                    textStyle = Typography.titleLarge()
                        .copy(fontSize = 14.sp, fontWeight = FontWeight(700))
                )
            }
        }
    }
}