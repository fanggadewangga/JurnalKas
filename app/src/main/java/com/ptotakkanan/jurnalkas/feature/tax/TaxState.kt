package com.ptotakkanan.jurnalkas.feature.tax

data class TaxState(
    val nik: String = "",
    val isHasNpwp: Boolean = false,
    val marriageStatus: Boolean = false,
    val salary: Long = 0L,
    val isHasResult: Boolean = false
)
