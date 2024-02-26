package com.ptotakkanan.jurnalkas.feature.wallet.detail

import com.ptotakkanan.jurnalkas.data.model.InRangeWalletDetail
import com.ptotakkanan.jurnalkas.domain.WalletDetail

data class WalletDetailState(
    val isLoading: Boolean = false,
    val walletDetail: WalletDetail? = null,
    val isDatePickerOpen: Boolean = false,
    val startDateRange: String = "",
    val endDateRange: String = "",
    val isSheetOpen: Boolean = false,
    val walletDetailInRange: InRangeWalletDetail? = null,
)
