package com.shift.shiftfinal.presentation.state

import com.shift.shiftfinal.domain.entity.LoanEntity

sealed interface MyLoansScreenState {

    data object Loading : MyLoansScreenState

    data class Content(val loanList: List<LoanEntity>) : MyLoansScreenState

    data class Error(val message: String) : MyLoansScreenState


}