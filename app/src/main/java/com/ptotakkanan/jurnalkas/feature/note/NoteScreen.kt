package com.ptotakkanan.jurnalkas.feature.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.common.components.AppButton
import com.ptotakkanan.jurnalkas.feature.common.components.AppDialog
import com.ptotakkanan.jurnalkas.feature.common.components.AppSearchField
import com.ptotakkanan.jurnalkas.feature.common.components.AppText
import com.ptotakkanan.jurnalkas.feature.common.util.ObserveAsEvents
import com.ptotakkanan.jurnalkas.feature.note.components.NoteItem
import com.ptotakkanan.jurnalkas.theme.Typography
import com.ptotakkanan.jurnalkas.theme.blue60
import com.ptotakkanan.jurnalkas.theme.primary10
import com.ptotakkanan.jurnalkas.theme.primary20
import es.dmoral.toasty.Toasty

@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NoteViewModel = viewModel(),
) {

    val state by viewModel.state
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.eventFlow) { event ->
        when (event) {
            is NoteViewModel.UiEvent.ShowErrorToast -> Toasty.error(context, event.message).show()
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp, horizontal = 24.dp)
            .background(color = blue60)
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
        Spacer(modifier = Modifier.height(48.dp))
        AppText(
            text = "Temukan Catatanmu",
            textStyle = Typography.titleMedium()
                .copy(fontSize = 24.sp, fontWeight = FontWeight(700)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(48.dp))
        AppSearchField(
            valueState = state.query,
            placeholder = "Masukkan kata kunci",
            borderColor = Color.White,
            elevation = 4.dp,
            contentPadding = 24.dp,
            textStyle = Typography.bodyMedium()
                .copy(fontSize = 12.sp, fontWeight = FontWeight(500)),
            leadingIcon = {
                AsyncImage(
                    model = R.drawable.ic_search_field,
                    contentDescription = "Search icon",
                    colorFilter = ColorFilter.tint(color = Color.Gray),
                    modifier = Modifier.size(20.dp)
                )
            },
            onValueChange = {
                viewModel.apply {
                    onEvent(NoteEvent.EnterSearchQuery(it))
                    onEvent(NoteEvent.SearchNote(it))
                }
            }
        )
        Spacer(modifier = Modifier.height(48.dp))
        if (state.isLoading)
            CircularProgressIndicator(
                color = primary20,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        else
            LazyColumn {
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )
                }
            }
    }
}