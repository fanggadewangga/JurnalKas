package com.ptotakkanan.jurnalkas.feature.category.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import com.ptotakkanan.jurnalkas.domain.OtherCategory
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

    private val _pageState = mutableIntStateOf(1)
    val pageState = _pageState

    private val _moreCategories = listOf(
        SelectionOption(OtherCategory("Profil", R.drawable.ic_profile), false),
        SelectionOption(OtherCategory("Book", R.drawable.ic_book), false),
        SelectionOption(OtherCategory("Dompet", R.drawable.ic_wallet_category), false),
        SelectionOption(OtherCategory("Cari", R.drawable.ic_search), false),
        SelectionOption(OtherCategory("Pengingat", R.drawable.ic_reminder), false),
        SelectionOption(OtherCategory("Kategori", R.drawable.ic_ticker), false),
        SelectionOption(OtherCategory("Tentang", R.drawable.ic_about), false),
        SelectionOption(OtherCategory("Google Drive", R.drawable.ic_gdrive), false),
        SelectionOption(OtherCategory("Premium", R.drawable.ic_badge), false),
    )

    val moreCategory: List<SelectionOption<OtherCategory>>
        get() = _moreCategories

    val repository = AppRepository()

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.NavigateToMoreCategory -> {
                _pageState.intValue = 2
                viewModelScope.launch { channel.send(UiEvent.NavigateToMoreCategories) }
            }

            CategoryEvent.FetchCategories -> viewModelScope.launch {
                repository.fetchCategories().collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                        is Resource.Error -> {
                            _state.value = _state.value.copy(isLoading = true)
                            channel.send(
                                UiEvent.ShowErrorToast(
                                    result.message ?: "Terjadi Kesalahan"
                                )
                            )
                        }

                        is Resource.Success -> _state.value = _state.value.copy(
                            isLoading = false,
                            categories = result.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowErrorToast(val message: String) : UiEvent()
        data object NavigateToMoreCategories : UiEvent()
    }

    init {
        onEvent(CategoryEvent.FetchCategories)
    }
}