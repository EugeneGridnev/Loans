package com.shift.shiftfinal.presentation.state

sealed interface LoginScreenState {

    data object Loading : LoginScreenState

    data class RegistrationContent(
        val login: Field<String>,
        val password: Field<String>,
        val repeatPassword: Field<String>,
        val error: String? = null
    ) : LoginScreenState {
        val isValid = login.error == null && password.error == null && repeatPassword.error == null
    }

    data class LoginContent(
        val login: Field<String>,
        val password: Field<String>,
        val error: String? = null
    ) : LoginScreenState {
        val isValid = login.error == null && password.error == null
    }

}
