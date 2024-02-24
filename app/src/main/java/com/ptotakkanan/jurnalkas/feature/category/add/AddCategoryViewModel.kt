package com.ptotakkanan.jurnalkas.feature.category.add

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.model.Category
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class AddCategoryViewModel : ViewModel() {

    private val _state = mutableStateOf(AddCategoryState())
    val state = _state

    val repository = AppRepository()

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: AddCategoryEvent) {
        when (event) {
            is AddCategoryEvent.EnterCategory -> {
                _state.value = _state.value.copy(name = event.value)
            }

            is AddCategoryEvent.ChooseCategory -> {
                _state.value.imageOptions.forEach { it.selected = false }
                _state.value.imageOptions.find { it.option == event.value.option }?.selected = true
            }

            is AddCategoryEvent.EnterDescription -> _state.value =
                _state.value.copy(description = event.value)

            is AddCategoryEvent.EnterExample -> _state.value =
                _state.value.copy(example = event.value)

            AddCategoryEvent.AddNewCategory -> {
                val selectedImage = _state.value.imageOptions.find { it.selected }?.option
                    ?: _state.value.imageOptions.first().option
                val body = Category(
                    name = _state.value.name,
                    description = _state.value.description,
                    example = _state.value.example,
                    imageUrl = selectedImage
                )

                viewModelScope.launch {
                    repository.addNewCategory(body).collectLatest { result ->
                        when (result) {
                            is Resource.Loading -> _state.value =
                                _state.value.copy(isLoading = true)

                            is Resource.Error -> {
                                _state.value = _state.value.copy(isLoading = false)
                                channel.send(
                                    UiEvent.ShowErrorToast(
                                        result.message ?: "Terjadi Kesalahan"
                                    )
                                )
                            }

                            is Resource.Success -> {
                                _state.value = _state.value.copy(isLoading = false)
                                channel.send(UiEvent.ShowSuccessToast("Sukses menambahkan kategori"))
                            }
                        }
                    }
                }
            }

            AddCategoryEvent.FetchImageOptions -> viewModelScope.launch {
                repository.fetchCategoryImages().collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                        is Resource.Error -> {
                            _state.value = _state.value.copy(isLoading = false)
                            channel.send(
                                UiEvent.ShowErrorToast(
                                    result.message ?: "Terjadi Kesalahan"
                                )
                            )
                        }

                        is Resource.Success -> {
                            val selectionOptions = mutableStateListOf<SelectionOption<String>>()
                            result.data?.forEach { image ->
                                if (image == result.data.first())
                                    selectionOptions.add(SelectionOption(image, true))
                                else
                                    selectionOptions.add(SelectionOption(image, false))
                            }
                            _state.value = _state.value.copy(
                                isLoading = false,
                                imageOptions = selectionOptions
                            )
                        }
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowErrorToast(val message: String) : UiEvent()
        data class ShowSuccessToast(val message: String) : UiEvent()
    }

    init {
        onEvent(AddCategoryEvent.FetchImageOptions)
    }
}