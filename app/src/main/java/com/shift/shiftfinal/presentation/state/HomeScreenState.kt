package com.shift.shiftfinal.presentation.state

import com.shift.shiftfinal.domain.entity.LoanEntity

sealed interface HomeScreenState {

    data object Initial : HomeScreenState

    data object Loading : HomeScreenState

    data class Content(
        val loanHomeConditionEntity: LoanHomeConditionEntity,
        val loanList: List<LoanEntity>,
    ) : HomeScreenState

    data class Error(val message: String) : HomeScreenState

    data class LoanHomeConditionEntity(
        val maxAmount: Int,
        val minAmount: Int,
        val amount: Int,
        val percent: Double,
        val period: Int,
        val errorStatus: String = ""
    )
}