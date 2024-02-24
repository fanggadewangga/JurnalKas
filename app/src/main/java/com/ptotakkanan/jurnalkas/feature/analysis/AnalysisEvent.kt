package com.ptotakkanan.jurnalkas.feature.analysis

sealed class AnalysisEvent {
    data class SwitchTab(val tab: String): AnalysisEvent()
    data class FetchIncome(val walletId: String): AnalysisEvent()
    data class FetchOutcome(val walletId: String): AnalysisEvent()
}