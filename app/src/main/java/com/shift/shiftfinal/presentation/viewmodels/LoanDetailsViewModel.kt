package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.usecase.GetLoanDetailsUseCase
import com.shift.shiftfinal.domain.usecase.LogoutUserUseCase
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import com.shift.shiftfinal.presentation.state.LoanDetailsScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class LoanDetailsViewModel @AssistedInject constructor(
    @Assisted private val loanId: Int,
    private val activityRouter: ActivityRouter,
    private val mainFragmentRouter: MainFragmentRouter,
    private val getLoanDetailsUseCase: GetLoanDetailsUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(loanId: Int): LoanDetailsViewModel
    }

    private val _state = MutableLiveData<LoanDetailsScreenState>(LoanDetailsScreenState.Initial)
    val state: LiveData<LoanDetailsScreenState> = _state

    fun back() {
        mainFragmentRouter.back()
    }

    fun getLoanDetails(loanId: Int) {
        viewModelScope.launch {
            _state.value = LoanDetailsScreenState.Loading
            try {
                val loanDetails = getLoanDetailsUseCase(loanId)
                _state.value =
                    LoanDetailsScreenState.Content(loanDetails)
            } catch (e: AuthException) {
                logoutUserUseCase()
                activityRouter.openLoginScreen()
                return@launch
            } catch (_: ApiException) {
                _state.value = LoanDetailsScreenState.Error("Сервис временно не работает!")
                return@launch
            }
        }
    }
}