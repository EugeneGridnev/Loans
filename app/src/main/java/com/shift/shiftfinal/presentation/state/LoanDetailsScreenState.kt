package com.shift.shiftfinal.presentation.state

import com.shift.shiftfinal.domain.entity.LoanEntity

sealed interface LoanDetailsScreenState {

    data object Loading : LoanDetailsScreenState

    data class Content(val loanList: LoanEntity) : LoanDetailsScreenState

    data class Error(val message: String) : LoanDetailsScreenState


}