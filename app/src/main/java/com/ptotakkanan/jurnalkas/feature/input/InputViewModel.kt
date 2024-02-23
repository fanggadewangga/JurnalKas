package com.ptotakkanan.jurnalkas.feature.input

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.core.ext.createTimeStamp
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.domain.Transaction
import com.ptotakkanan.jurnalkas.domain.Wallet
import com.ptotakkanan.jurnalkas.feature.util.date.DateFormat
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class InputViewModel : ViewModel() {

    private val _outcomeCategories = listOf(
        SelectionOption(Category("Makan", R.drawable.ic_food), true),
        SelectionOption(Category("Jajan", R.drawable.ic_snack), false),
        SelectionOption(Category("Harian", R.drawable.ic_broom), false),
        SelectionOption(Category("Lalu Lintas", R.drawable.ic_traffic), false),
        SelectionOption(Category("Hadiah", R.drawable.ic_gift), false),
        SelectionOption(Category("Rumah", R.drawable.ic_house), false),
        SelectionOption(Category("Pendidikan", R.drawable.ic_education), false),
        SelectionOption(Category("Hiburan", R.drawable.ic_entertaiment), false),
    ).toMutableStateList()

    val outcomeCategories: List<SelectionOption<Category>>
        get() = _outcomeCategories

    private val _tabOptions = listOf(
        SelectionOption("Dompet", false),
        SelectionOption("Kategori", true),
    ).toMutableStateList()

    val tabOptions: List<SelectionOption<String>>
        get() = _tabOptions

    private val _numpadNumbers = listOf("7", "8", "9", "4", "5", "6", "1", "2", "3")
    val numpadNumbers: List<String>
        get() = _numpadNumbers

    private val _state = mutableStateOf(InputState())
    val state = _state

    private val repository = AppRepository()

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: InputEvent) {
        when (event) {
            is InputEvent.EnterIncomeDescription -> {
                _state.value = _state.value.copy(incomeDescription = event.value)
            }

            is InputEvent.EnterOutcomeDescription -> {
                _state.value = _state.value.copy(outcomeDescription = event.value)
            }

            is InputEvent.ChooseOutcomeCategory -> {
                _outcomeCategories.forEach { it.selected = false }
                _outcomeCategories.find { it.option == event.value.option }?.selected = true
                _state.value =
                    _state.value.copy(chosenOutcomeCategory = event.value.option.category)
            }

            is InputEvent.ChooseWalletCategory -> {
                _state.value.wallet.forEach { it.selected = false }
                _state.value.wallet.find { it.option == event.value.option }?.selected = true
                _state.value = _state.value.copy(chosenWallet = event.value.option)
            }

            is InputEvent.EnterIncomeDate -> {
                _state.value = _state.value.copy(incomeDate = event.value)
            }

            is InputEvent.EnterOutcomeDate -> {
                _state.value = _state.value.copy(outcomeDate = event.value)
            }

            is InputEvent.EnterIncomeNominal -> {
                _state.value = _state.value.copy(incomeNominal = event.value)
            }

            is InputEvent.EnterOutcomeNominal -> {
                _state.value = _state.value.copy(outcomeNominal = event.value)
            }

            is InputEvent.SwitchTab -> {
                _tabOptions.forEach { it.selected = false }
                _tabOptions.find { it.option == event.value.option }?.selected = true
                _state.value = _state.value.copy(selectedTab = event.value.option)
            }

            is InputEvent.DeleteNominalByNumpad -> {

            }

            is InputEvent.EnterNominalByNumpad -> {

            }

            InputEvent.FetchWallets -> {
                viewModelScope.launch {
                    repository.fetchWallets().collect { result ->
                        when (result) {
                            is Resource.Loading -> _state.value =
                                _state.value.copy(isLoading = true)

                            is Resource.Error -> {}
                            is Resource.Success -> {
                                val selectionOptions = mutableStateListOf<SelectionOption<Wallet>>()
                                result.data?.forEach { wallet ->
                                    if (wallet == result.data.first())
                                        selectionOptions.add(SelectionOption(wallet, true))
                                    else
                                        selectionOptions.add(SelectionOption(wallet, false))
                                }
                                _state.value = _state.value.copy(
                                    isLoading = false,
                                    wallet = selectionOptions,
                                    chosenWallet = result.data?.first()
                                )
                            }
                        }
                    }
                }
            }

            InputEvent.AddIncome -> {
                viewModelScope.launch {
                    val transaction = Transaction(
                        walletId = _state.value.chosenWallet?.walletId ?: "",
                        title = _state.value.title,
                        description = _state.value.incomeDescription,
                        nominal = _state.value.incomeNominal.toLong(),
                        isIncome = true,
                        date = createTimeStamp(DateFormat.DATE)
                    )
                    repository.addTransaction(transaction).collectLatest { result ->
                        when(result) {
                            is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                            is Resource.Error -> {
                                _state.value = _state.value.copy(isLoading = false)
                                channel.send(UiEvent.ShowErrorToast(result.message ?: "Terjadi kesalahan"))
                            }
                            is Resource.Success -> {
                                _state.value = _state.value.copy(isLoading = false)
                                channel.send(UiEvent.ShowSuccessToast("Sukses menambahkan transaksi"))
                            }
                        }
                    }
                }
            }

            InputEvent.AddOutcome -> {
                viewModelScope.launch {
                    val transaction = Transaction(
                        walletId = _state.value.chosenWallet?.walletId ?: "",
                        description = _state.value.outcomeDescription,
                        nominal = _state.value.outcomeNominal.toLong(),
                        isIncome = false,
                        date = createTimeStamp(DateFormat.DATE)
                    )
                    repository.addTransaction(transaction).collectLatest { result ->
                        when(result) {
                            is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                            is Resource.Error -> {
                                _state.value = _state.value.copy(isLoading = false)
                                channel.send(UiEvent.ShowErrorToast(result.message ?: "Terjadi kesalahan"))
                            }
                            is Resource.Success -> {
                                _state.value = _state.value.copy(isLoading = false)
                                channel.send(UiEvent.ShowSuccessToast("Sukses menambahkan transaksi"))
                            }
                        }
                    }
                }
            }

            is InputEvent.EnterTitle -> {
                _state.value = _state.value.copy(title = event.value)
            }
        }
    }

    sealed class UiEvent {
        data class ShowErrorToast(val message: String): UiEvent()
        data class ShowSuccessToast(val message: String): UiEvent()
    }

    init {
        onEvent(InputEvent.FetchWallets)
    }
}