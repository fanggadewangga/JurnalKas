package com.ptotakkanan.jurnalkas.feature.wallet.detail

import com.ptotakkanan.jurnalkas.domain.WalletDetail

data class WalletDetailState(
    val isLoading: Boolean = false,
    val walletDetail: WalletDetail? = null
)
