package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.usecase.GetMyLoansUseCase
import com.shift.shiftfinal.domain.usecase.LogoutUserUseCase
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import com.shift.shiftfinal.presentation.state.HomeScreenState
import com.shift.shiftfinal.presentation.state.LoanDetailsScreenState
import com.shift.shiftfinal.presentation.state.MyLoansScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyLoansViewModel @Inject constructor(
    private val activityRouter: ActivityRouter,
    private val mainFragmentRouter: MainFragmentRouter,
    private val getMyLoansUseCase: GetMyLoansUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<MyLoansScreenState>(MyLoansScreenState.Loading)
    val state: LiveData<MyLoansScreenState> = _state

    fun getMyLoans() {
        viewModelScope.launch {
            _state.value = MyLoansScreenState.Loading
            try {
                val myLoans = getMyLoansUseCase()
                _state.value =
                    MyLoansScreenState.Content(myLoans)
            }catch (e: AuthException) {
                logoutUserUseCase()
                activityRouter.openLoginScreen()
                return@launch
            } catch (_: ApiException) {
                _state.value = MyLoansScreenState.Error("Сервис временно не работает!")
                return@launch
            }
        }
    }

    fun openLoanDetails(loanId: Int) {
        mainFragmentRouter.openLoanDetailsScreen(loanId)
    }

    fun back() {
        mainFragmentRouter.back()
    }
}