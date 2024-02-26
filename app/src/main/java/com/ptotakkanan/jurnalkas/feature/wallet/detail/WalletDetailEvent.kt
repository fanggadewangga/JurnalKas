package com.ptotakkanan.jurnalkas.feature.wallet.detail

sealed class WalletDetailEvent {
    data class OpenDatePicker(val isOpen: Boolean): WalletDetailEvent()
    data class SetRange(val start: String, val end: String): WalletDetailEvent()
    data class SetSheetOpen(val isOpen: Boolean): WalletDetailEvent()
    data class FetchInRangeWalletDetail(val start: String, val end: String, val walletId: String): WalletDetailEvent()
}