package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.usecase.CheckUserLoginUseCase
import com.shift.shiftfinal.domain.usecase.GetLoanConditionUseCase
import com.shift.shiftfinal.domain.usecase.LogoutUserUseCase
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import com.shift.shiftfinal.presentation.state.SplashScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class LoanDetailsViewModel @AssistedInject constructor(
    @Assisted private val loanId: Int,
    private val mainFragmentRouter: MainFragmentRouter
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(loanId: Int): LoanDetailsViewModel
    }

    fun back() {
        mainFragmentRouter.back()
    }
}