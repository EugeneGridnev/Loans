package com.shift.shiftfinal.presentation.state

import com.shift.shiftfinal.domain.entity.LoanEntity

sealed interface LoanApplicationScreenState {

    data object Loading : LoanApplicationScreenState

    data class Content(val loanList: List<LoanEntity>) : LoanApplicationScreenState

    data class Error(val message: String) : LoanApplicationScreenState

}