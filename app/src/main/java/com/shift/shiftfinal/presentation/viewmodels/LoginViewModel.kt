package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shift.shiftfinal.R
import com.shift.shiftfinal.domain.entity.AuthEntity
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.exceptions.UserNameTakenException
import com.shift.shiftfinal.domain.usecase.LoginUserUseCase
import com.shift.shiftfinal.domain.usecase.LogoutUserUseCase
import com.shift.shiftfinal.domain.usecase.RegisterUserUseCase
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.presentation.state.Field
import com.shift.shiftfinal.presentation.state.LoginScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val activityRouter: ActivityRouter,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
) : ViewModel() {

    companion object {
        private fun defaultLogin() = LoginScreenState.LoginContent(Field(""), Field(""))
        private fun defaultRegistration() =
            LoginScreenState.RegistrationContent(Field(""), Field(""), Field(""))
    }

    private val _state = MutableLiveData<LoginScreenState>(defaultLogin())
    val state: LiveData<LoginScreenState> = _state

    fun startLogin() {
        _state.value = defaultLogin()
    }

    fun startRegistration() {
        _state.value = defaultRegistration()
    }

    fun setLogin(value: String) {
        _state.value.let {
            when (it) {
                is LoginScreenState.LoginContent -> {
                    _state.value = it.copy(
                        login = Field(value, true),
                        error = null
                    )
                }

                is LoginScreenState.RegistrationContent -> {
                    _state.value = it.copy(
                        login = Field(value, true),
                        error = null
                    )
                }

                else -> {}
            }
        }
    }

    fun setPassword(value: String) {
        _state.value.let {
            when (it) {
                is LoginScreenState.LoginContent -> {
                    _state.value = it.copy(
                        password = Field(value, true),
                        error = null
                    )
                }

                is LoginScreenState.RegistrationContent -> {
                    val errorRes = when {
                        it.password.value != value -> R.string.passwords_not_the_same
                        else -> null
                    }
                    _state.value = it.copy(
                        password = Field(value, true),
                        repeatPassword = it.repeatPassword.copy(error = errorRes),
                        error = null
                    )
                }

                else -> {}
            }
        }
    }

    fun setRepeatPassword(value: String) {
        _state.value.let {
            when (it) {
                is LoginScreenState.RegistrationContent -> {
                    val errorRes = when {
                        it.password.value != value -> R.string.passwords_not_the_same
                        else -> null
                    }
                    _state.value = it.copy(
                        repeatPassword = Field(value, true, errorRes),
                        error = null
                    )
                }

                else -> {}
            }
        }
    }

    fun finishAuthenticate() {
        viewModelScope.launch {
            val previousState = _state.value

            if (previousState is LoginScreenState.Loading) {
                return@launch
            }

            val auth: AuthEntity
            try {
                when (previousState) {
                    is LoginScreenState.LoginContent -> {
                        if (!previousState.isValid) {
                            return@launch
                        }
                        _state.value = LoginScreenState.Loading
                        auth = AuthEntity(previousState.login.value, previousState.password.value)
                        loginUserUseCase(auth)
                        activityRouter.openMainScreen()
                        return@launch
                    }

                    is LoginScreenState.RegistrationContent -> {
                        if (!previousState.isValid) {
                            return@launch
                        }
                        _state.value = LoginScreenState.Loading
                        auth = AuthEntity(previousState.login.value, previousState.password.value)
                        registerUserUseCase(auth)
                        loginUserUseCase(auth)
                        activityRouter.openOnboarding()
                        return@launch
                    }

                    else -> return@launch
                }
            } catch (e: UserNameTakenException) {
                logoutUserUseCase()
                _state.value = when (previousState) {

                    is LoginScreenState.RegistrationContent -> previousState.copy(
                        login = previousState.login.copy(
                            error = R.string.user_name_taken_registeration_error
                        )
                    )

                    else -> previousState
                }

            } catch (e: AuthException) {
                logoutUserUseCase()
                _state.value = when (previousState) {

                    is LoginScreenState.LoginContent ->
                        previousState.copy(error = "Неверный пароль или логин")
                    is LoginScreenState.RegistrationContent ->
                        previousState.copy(error = "Проблемы API")
                    else -> previousState
                }

            } catch (_: ApiException) {
                _state.value = when (previousState) {

                    is LoginScreenState.LoginContent -> previousState.copy(error = "Проблемы API")
                    is LoginScreenState.RegistrationContent ->
                        previousState.copy(error = "Проблемы API")
                    else -> previousState
                }
                return@launch
            }

        }
    }

}