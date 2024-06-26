package com.ptotakkanan.jurnalkas.feature.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary0

@Composable
fun AppDialog(
    dialogContent: (@Composable () -> Unit),
    setShowDialog: (Boolean) -> Unit,
    isWithButton: Boolean = false,
    onCancelClicked: () -> Unit,
    onConfirmClicked: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = { setShowDialog(false) }, properties = properties) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            modifier = modifier
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                dialogContent.invoke()

                // Button
                if (isWithButton) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        AppButton(
                            onClick = { onCancelClicked.invoke() },
                            backgroundColor = Color.White,
                            borderColor = primary0,
                            borderWidth = 1.dp,
                            modifier = Modifier.width(120.dp)
                        ) {
                            AppText(
                                text = "Batal",
                                textStyle = Typography.labelLarge(),
                                color = primary0
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        AppButton(
                            onClick = { onConfirmClicked.invoke() },
                            modifier = Modifier.width(120.dp)
                        ) {
                            AppText(
                                text = "Konfirmasi",
                                textStyle = Typography.labelLarge(),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}