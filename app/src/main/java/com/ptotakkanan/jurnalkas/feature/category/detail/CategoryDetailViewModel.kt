package com.ptotakkanan.jurnalkas.feature.category.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CategoryDetailViewModel : ViewModel() {

    private val _state = mutableStateOf(CategoryDetailState())
    val state: State<CategoryDetailState> = _state

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    private val repository = AppRepository()

    fun fetchCategoryDetail(categoryId: String) {
        viewModelScope.launch {
            repository.fetchCategoryDetail(categoryId).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                    is Resource.Error -> {
                        _state.value = _state.value.copy(isLoading = true)
                        channel.send(UiEvent.ShowErrorToast(result.message ?: "Terjadi kesalahan"))
                    }
                    is Resource.Success -> _state.value = _state.value.copy(isLoading = false, category = result.data)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowErrorToast(val message: String) : UiEvent()
    }
}