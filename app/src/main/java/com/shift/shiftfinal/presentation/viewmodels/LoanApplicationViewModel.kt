package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class LoanApplicationViewModel @AssistedInject constructor(
    @Assisted private val loanCondition: LoanConditionEntity,
    private val activityRouter: ActivityRouter,
    private val mainFragmentRouter: MainFragmentRouter
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(loanCondition: LoanConditionEntity): LoanApplicationViewModel
    }

    fun openLoanAccepted(loanAmount: Int) {
        activityRouter.openLoanAcceptedScreen()
    }

    fun openLoanDenied() {
        activityRouter.openLoanDeniedScreen()
    }

    fun back() {
        mainFragmentRouter.back()
    }
}