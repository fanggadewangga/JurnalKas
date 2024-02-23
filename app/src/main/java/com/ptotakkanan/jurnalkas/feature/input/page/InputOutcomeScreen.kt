package com.ptotakkanan.jurnalkas.feature.input.page

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.core.ext.convertDateFormat
import com.ptotakkanan.jurnalkas.feature.category.components.CategoryItem
import com.ptotakkanan.jurnalkas.feature.common.components.AppDialog
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.common.util.ObserveAsEvents
import com.ptotakkanan.jurnalkas.feature.input.InputEvent
import com.ptotakkanan.jurnalkas.feature.input.InputState
import com.ptotakkanan.jurnalkas.feature.input.InputViewModel
import com.ptotakkanan.jurnalkas.feature.input.components.CustomNumpad
import com.ptotakkanan.jurnalkas.feature.util.date.DateFormat
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.primary10
import com.ptotakkanan.jurnalkas.theme.primary20
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import es.dmoral.toasty.Toasty
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InputOutcomeScreen(navController: NavController, viewModel: InputViewModel, state: InputState) {

    val calendarState = rememberMaterialDialogState()
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.eventFlow) { event ->
        when(event) {
            is InputViewModel.UiEvent.ShowErrorToast -> Toasty.error(context, event.message).show()
            is InputViewModel.UiEvent.ShowSuccessToast -> Toasty.success(context, event.message).show()
        }
    }

    if (state.isLoading)
        AppDialog(
            dialogContent = { CircularProgressIndicator(color = primary10) },
            setShowDialog = {},
            onCancelClicked = {},
            onConfirmClicked = {},
            modifier = Modifier.size(120.dp)
        )

    // Calendar Picker
    MaterialDialog(
        shape = RoundedCornerShape(16.dp),
        dialogState = calendarState,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    fontSize = 10.sp,
                    color = primary20
                ),
            ) {
                calendarState.hide()
            }
            negativeButton(
                text = "Batal",
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    fontSize = 10.sp,
                    color = primary20
                ),
            ) {
                calendarState.hide()
            }
        },
        backgroundColor = Color.White
    ) {
        datepicker(
            title = "Pilih Tanggal",
            initialDate = LocalDate.now(),
            waitForPositiveButton = true,
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = primary20,
                headerTextColor = Color.White,
                dateActiveBackgroundColor = primary20,
                dateActiveTextColor = Color.White
            ),
            allowedDateValidator = { date ->
                date <= LocalDate.now()
            }
        ) {
            viewModel.onEvent(
                InputEvent.EnterOutcomeDate(
                    it.toString().convertDateFormat(DateFormat.DEFAULT, DateFormat.FRONTEND_DATE)
                )
            )
        }
    }

    Spacer(Modifier.height(32.dp))
    FlowRow(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        maxItemsInEachRow = 3,
        modifier = Modifier.fillMaxWidth()
    ) {
        viewModel.outcomeCategories.forEach { category ->
            CategoryItem(
                name = category.option.category,
                icon = category.option.icon,
                selected = category.selected,
                modifier = Modifier
                    .size(90.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = Color.White),
                        onClick = { viewModel.onEvent(InputEvent.ChooseOutcomeCategory(category)) }
                    )
            )
        }
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .size(90.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = Color.White),
                    onClick = { }
                )
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = R.drawable.ic_add,
                    contentDescription = "Add icon",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = R.drawable.ic_category,
                        contentDescription = "Category box",
                        colorFilter = ColorFilter.tint(primary20)
                    )
                    AppText(
                        text = state.chosenOutcomeCategory,
                        textStyle = Typography.titleSmall().copy(fontSize = 12.sp),
                        color = primary20
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = state.chosenWallet,
                        contentDescription = "Category box",
                        colorFilter = ColorFilter.tint(primary20),
                        modifier = Modifier.size(16.dp)
                    )
                    AppText(
                        text = state.chosenWallet?.name ?: "",
                        textStyle = Typography.titleSmall().copy(fontSize = 12.sp),
                        color = primary20
                    )
                }
            }

            // Description text field
            Spacer(modifier = Modifier.height(24.dp))
            AppText(
                text = "Keterangan",
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.raleway)),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500)
                ),
                color = Color(0xFF003566)
            )
            Spacer(modifier = Modifier.height(12.dp))
            BasicTextField(
                value = state.outcomeDescription,
                onValueChange = { viewModel.onEvent(InputEvent.EnterOutcomeDescription(it)) },
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    fontSize = 12.sp,
                    color = Color(0xFF003566)
                )
            )
            Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())

            // Nominal text field
            Spacer(modifier = Modifier.height(24.dp))
            AppText(
                text = "Nominal",
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.raleway)),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500)
                ),
                color = Color(0xFF003566)
            )
            Spacer(modifier = Modifier.height(12.dp))
            BasicTextField(
                value = state.outcomeNominal,
                onValueChange = { viewModel.onEvent(InputEvent.EnterOutcomeNominal(it)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    fontSize = 12.sp,
                    color = Color(0xFF003566)
                )
            )
            Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { calendarState.show() }
                ) {
                    AsyncImage(
                        model = R.drawable.ic_calendar,
                        contentDescription = "Calendar icon",
                        modifier = Modifier.size(24.dp)
                    )
                    AppText(
                        text = state.outcomeDate,
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            fontSize = 10.sp
                        ),
                        color = primary20
                    )
                }

                // Numpad
                FlowRow(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    maxItemsInEachRow = 3,
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    viewModel.numpadNumbers.forEach { number ->
                        CustomNumpad(
                            text = number,
                            onNumberClick = {},
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 1.dp)
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(color = Color.White),
                                onClick = { viewModel.onEvent(InputEvent.EnterOutcomeNominal(".")) }
                            )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AppText(
                                text = ".",
                                textStyle = Typography.titleMedium().copy(fontSize = 20.sp),
                                color = primary20
                            )
                        }
                    }
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(color = Color.White),
                                onClick = { viewModel.onEvent(InputEvent.EnterOutcomeNominal("0")) }
                            )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AppText(
                                text = "0",
                                textStyle = Typography.titleMedium().copy(fontSize = 20.sp),
                                color = primary20
                            )
                        }
                    }
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(color = Color.White),
                                onClick = { }
                            )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AsyncImage(
                                model = R.drawable.ic_delete,
                                contentDescription = "Delete icon",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                // Check
                AsyncImage(
                    model = R.drawable.ic_check,
                    contentDescription = "Check icon",
                    colorFilter = ColorFilter.tint(primary20),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            viewModel.onEvent(InputEvent.AddOutcome)
                        }
                )
            }
        }
    }
}