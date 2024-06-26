package com.shift.shiftfinal.presentation.state

sealed interface LoanApplicationScreenState {

    data object Loading : LoanApplicationScreenState

    data class Content(
        val name: Field<String>,
        val secondName: Field<String>,
        val phone: Field<String>
    ) : LoanApplicationScreenState {
        val isValid = name.error == null && secondName.error == null && phone.error == null
    }
}