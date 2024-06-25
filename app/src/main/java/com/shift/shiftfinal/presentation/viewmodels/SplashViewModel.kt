package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.usecase.CheckUserLoginUseCase
import com.shift.shiftfinal.domain.usecase.GetLoanConditionUseCase
import com.shift.shiftfinal.domain.usecase.LogoutUserUseCase
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.presentation.state.SplashScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val activityRouter: ActivityRouter,
    private val loanConditionUseCase: GetLoanConditionUseCase,
    private val checkUserLoginUseCase: CheckUserLoginUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<SplashScreenState>(SplashScreenState.Loading)
    val state: LiveData<SplashScreenState> = _state

    fun checkUserLoggedIn() {
        viewModelScope.launch {
            _state.value = SplashScreenState.Loading
            if (!checkUserLoginUseCase()) {
                delay(1000)
                activityRouter.openLoginScreen()
                return@launch
            }
            val loanConditions = try {
                loanConditionUseCase()
            } catch (e: AuthException) {
                logoutUserUseCase()
                activityRouter.openLoginScreen()
                return@launch
            }
            catch (_: ApiException) {
                _state.value = SplashScreenState.Error("Сервис временно не работает!")
                return@launch
            }
            activityRouter.openMainScreen(loanConditions)
        }
    }
}