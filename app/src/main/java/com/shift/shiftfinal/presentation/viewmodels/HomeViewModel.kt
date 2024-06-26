package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.usecase.GetLoanConditionUseCase
import com.shift.shiftfinal.domain.usecase.GetMyLoansUseCase
import com.shift.shiftfinal.domain.usecase.LogoutUserUseCase
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import com.shift.shiftfinal.presentation.state.HomeScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class HomeViewModel @Inject constructor(
    private val activityRouter: ActivityRouter,
    private val mainFragmentRouter: MainFragmentRouter,
    private val getMyLoansUseCase: GetMyLoansUseCase,
    private val conditionUseCase: GetLoanConditionUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<HomeScreenState>(HomeScreenState.Initial)
    val state: LiveData<HomeScreenState> = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = HomeScreenState.Loading
            try {
                val loanCondition = conditionUseCase()
                val myLoans = getMyLoansUseCase()
                _state.value =
                    HomeScreenState.Content(
                        HomeScreenState.LoanHomeConditionEntity(
                            maxAmount = loanCondition.maxAmount,
                            minAmount = 100,
                            amount = 100,
                            percent = loanCondition.percent,
                            period = loanCondition.period
                        ),
                        myLoans
                    )

            } catch (e: AuthException) {
                logoutUserUseCase()
                activityRouter.openLoginScreen()
                return@launch
            } catch (_: ApiException) {
                _state.value = HomeScreenState.Error("Сервис временно не работает!")
                return@launch
            }
        }
    }

    fun setAmount(value: String) {
        _state.value.let {
            when (it) {
                is HomeScreenState.Content -> {
                    val errorRes = when {
                        it.loanHomeConditionEntity.amount > it.loanHomeConditionEntity.maxAmount ->
                            "Максимум ${it.loanHomeConditionEntity.maxAmount} Р"

                        it.loanHomeConditionEntity.amount < it.loanHomeConditionEntity.minAmount ->
                            "Минимум ${it.loanHomeConditionEntity.minAmount} Р"

                        else -> ""
                    }
                    val newState = it.copy(
                        loanHomeConditionEntity = it.loanHomeConditionEntity.copy(
                            amount = value.takeIf { it.isNotBlank() }?.toInt() ?: 0,
                            errorStatus = errorRes
                        )
                    )
                    if (newState != it) {
                        _state.value = newState
                    }
                }

                else -> {}
            }
        }
    }


    fun openOnboarding() {
        activityRouter.openOnboardingFromMain()
    }

    fun openMyLoans() {
        mainFragmentRouter.openMyLoansScreen()
    }

    fun openLoanInfo(loanId:Int) {
        mainFragmentRouter.openLoanDetailsScreen(loanId)
    }

    fun openLoanApplication() {
        _state.value.let {
            when (it) {
                is HomeScreenState.Content -> {
                    mainFragmentRouter.openLoanApplicationScreen(
                        LoanConditionEntity(
                            maxAmount = it.loanHomeConditionEntity.amount,
                            percent = it.loanHomeConditionEntity.percent,
                            period = it.loanHomeConditionEntity.period
                        )
                    )
                }

                else -> {}
            }
        }

    }
}