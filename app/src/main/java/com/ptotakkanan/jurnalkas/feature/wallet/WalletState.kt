package com.ptotakkanan.jurnalkas.feature.wallet

import com.ptotakkanan.jurnalkas.domain.Wallet

data class WalletState(
    val wallets: List<Wallet> = emptyList(),
    val isLoading: Boolean = false,
)
