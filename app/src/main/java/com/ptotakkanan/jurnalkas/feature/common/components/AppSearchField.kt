package com.ptotakkanan.jurnalkas.feature.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary0

@Composable
fun AppSearchField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    valueState: String,
    placeholder: String,
    backgroundColor: Color = White,
    elevation: Dp = 8.dp,
    borderColor: Color = LightGray,
    textStyle: TextStyle = Typography.bodyLarge(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(100.dp),
        elevation = CardDefaults.cardElevation(elevation),
        colors = CardDefaults.cardColors(containerColor = primary0),
        border = BorderStroke(width = 1.dp, color = borderColor),
        modifier = modifier
            .fillMaxWidth()
    ) {
        BasicTextField(
            modifier = modifier
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = valueState,
            onValueChange = onValueChange,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            cursorBrush = SolidColor(Color.Black),
            textStyle = textStyle,
            decorationBox = { innerTextField ->
                Row(
                    modifier.height(35.dp).padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (leadingIcon != null) leadingIcon()
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if (valueState.isEmpty())
                            AppText(text = placeholder, textStyle = textStyle, color = Gray)
                        innerTextField()
                    }
                    if (trailingIcon != null) trailingIcon()
                }
            }
        )
    }
}