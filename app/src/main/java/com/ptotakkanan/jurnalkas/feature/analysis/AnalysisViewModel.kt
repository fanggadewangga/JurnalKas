package com.ptotakkanan.jurnalkas.feature.analysis

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.domain.Analysis

class AnalysisViewModel: ViewModel() {

    private val _dummyIncome = listOf(
        Analysis(
            title = "Gaji",
            icon = R.drawable.ic_money,
            nominal = 5000000,
            isIncome = true,
            date = "4 Januari, 2023"
        ),
        Analysis(
            title = "Freelance",
            icon = R.drawable.ic_developer,
            nominal = 2414141,
            isIncome = true,
            date = "2 Januari, 2023"
        ),
        Analysis(
            title = "Bonus",
            icon = R.drawable.ic_money,
            nominal = 220424,
            isIncome = true,
            date = "6 Januari, 2023"
        ),
        Analysis(
            title = "Project",
            icon = R.drawable.ic_developer,
            nominal = 400000,
            isIncome = true,
            date = "11 Januari, 2023"
        ),
    ).toMutableStateList()

    private val _dummyOutcome = listOf(
        Analysis(
            title = "Makan",
            icon = R.drawable.ic_food,
            nominal = 35000,
            isIncome = false,
            date = "1 Januari, 2023"
        ),
        Analysis(
            title = "Bensin",
            icon = R.drawable.ic_traffic,
            nominal = 10000,
            isIncome = false,
            date = "3 Januari, 2023"
        ),
        Analysis(
            title = "Jajan",
            icon = R.drawable.ic_chocolate,
            nominal = 35000,
            isIncome = false,
            date = "1 Januari, 2023"
        ),
    ).toMutableStateList()

    val dummyIncome: List<Analysis>
        get() = _dummyIncome

    val dummyOutcome: List<Analysis>
        get() = _dummyOutcome
}