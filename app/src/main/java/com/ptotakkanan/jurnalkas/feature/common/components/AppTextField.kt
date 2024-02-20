package com.ptotakkanan.jurnalkas.feature.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary0

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = 4.dp,
    showWarningMessage: Boolean = false,
    warningMessage: String = "Field tidak boleh kosong!",
    placeHolder: String,
    label: String,
    required: Boolean = false,
    textStyle: TextStyle = Typography.bodyLarge(),
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textColor: Color = Color.Black,
    placeHolderColor: Color = Gray,
    disablePlaceHolderColor: Color = Gray,
    unfocusedLabelColor: Color = Gray,
    focusedLabelColor: Color = primary0,
    disabledTextColor: Color = Gray,
    backgroundColor: Color = Color.White,
    cursorColor: Color = Color.Black,
    errorCursorColor: Color = Red,
    focusedIndicatorColor: Color = primary0,
    unfocusedIndicatorColor: Color = Gray,
    disabledIndicatorColor: Color = Gray,
    errorIndicatorColor: Color = Red,
) {
    Column(verticalArrangement = Arrangement.spacedBy(contentSpacing)) {
        if (isPassword) {
            val passwordVisibility = remember { mutableStateOf(false) }

            OutlinedTextField(
                modifier = modifier,
                value = value,
                onValueChange = onValueChange,
                shape = shape,
                readOnly = readOnly,
                leadingIcon = leadingIcon,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }
                    ) {

                    }
                },
                isError = isError,
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = disabledTextColor,
                    cursorColor = cursorColor,
                    errorCursorColor = errorCursorColor,
                    focusedIndicatorColor = focusedIndicatorColor,
                    unfocusedIndicatorColor = unfocusedIndicatorColor,
                    disabledIndicatorColor = disabledIndicatorColor,
                    focusedLabelColor = focusedLabelColor,
                    unfocusedLabelColor = unfocusedLabelColor,
                    focusedPlaceholderColor = placeHolderColor,
                    unfocusedPlaceholderColor = disablePlaceHolderColor,
                    focusedContainerColor = backgroundColor,
                    unfocusedContainerColor = backgroundColor,
                    disabledContainerColor = backgroundColor,
                    errorIndicatorColor = errorIndicatorColor
                ),
                enabled = enabled,
                textStyle = textStyle,
                label = {
                    Row {
                        Text(
                            text = label,
                        )
                        if (required)
                            Text(
                                text = "*",
                                color = Red
                            )
                    }
                },
                placeholder = {
                    Text(
                        text = placeHolder,
                    )
                }
            )
        } else {
            OutlinedTextField(
                modifier = modifier,
                value = value,
                onValueChange = onValueChange,
                shape = shape,
                readOnly = readOnly,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = disabledTextColor,
                    cursorColor = cursorColor,
                    errorCursorColor = errorCursorColor,
                    focusedIndicatorColor = focusedIndicatorColor,
                    unfocusedIndicatorColor = unfocusedIndicatorColor,
                    disabledIndicatorColor = disabledIndicatorColor,
                    focusedLabelColor = focusedLabelColor,
                    unfocusedLabelColor = unfocusedLabelColor,
                    focusedPlaceholderColor = placeHolderColor,
                    unfocusedPlaceholderColor = disablePlaceHolderColor,
                    focusedContainerColor = backgroundColor,
                    unfocusedContainerColor = backgroundColor,
                    disabledContainerColor = backgroundColor,
                    errorIndicatorColor = errorIndicatorColor
                ),
                enabled = enabled,
                textStyle = textStyle,
                label = {
                    Row {
                        Text(
                            text = label,
                        )
                        if (required)
                            Text(
                                text = "*",
                                color = Red
                            )
                    }
                },
                placeholder = {
                    Text(
                        text = placeHolder,
                    )
                }
            )
        }

        if (showWarningMessage) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppText(
                    text = warningMessage,
                    color = primary0,
                    textStyle = Typography.bodySmall()
                )
            }
        }
    }
}

@Composable
fun AppBasicTextField(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = 4.dp,
    textFieldHeight: Dp = 56.dp,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    showWarningMessage: Boolean = false,
    warningMessage: String = "",
    borderColor: Color = LightGray,
    placeHolder: String,
    textStyle: TextStyle = Typography.bodyMedium(),
    value: String,
    onValueChange: (String) -> Unit,
    shape: Shape = RoundedCornerShape(100.dp),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    isWithBorder: Boolean = true,
    borderWidth: Dp = 1.dp,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    placeHolderColor: Color = Gray,
    disablePlaceHolderColor: Color = Gray,
    backgroundColor: Color = if (enabled) Color.White else Gray,

    ) {
    Column(verticalArrangement = Arrangement.spacedBy(contentSpacing)) {
        val customModifier: Modifier = if (isWithBorder) modifier
            .background(color = backgroundColor, shape = shape)
            .height(textFieldHeight)
            .border(
                width = borderWidth,
                color = if (isError) Red else borderColor,
                shape = shape
            ) else
            modifier
                .background(color = backgroundColor, shape = shape)
                .height(textFieldHeight)

        BasicTextField(
            modifier = customModifier,
            value = value,
            onValueChange = onValueChange,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            textStyle = textStyle,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = verticalAlignment,
                    modifier = modifier
                        .height(textFieldHeight)
                        .padding(12.dp),
                ) {
                    if (leadingIcon != null) leadingIcon()
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if (value.isEmpty())
                            AppText(
                                text = placeHolder,
                                color = if (enabled) placeHolderColor else disablePlaceHolderColor,
                                textStyle = Typography.bodyLarge(),
                            )
                        innerTextField()
                    }
                    if (trailingIcon != null) trailingIcon()
                }
            },
            enabled = enabled,
            readOnly = readOnly,
            maxLines = Int.MAX_VALUE,
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(Color.Black)
        )

        if (showWarningMessage) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(imageVector = Icons.Default.Warning, contentDescription = "Warning Icon")
                AppText(
                    text = warningMessage,
                    color = when {
                        isError -> Red
                        else -> Gray
                    }
                )
            }
        }
    }
}