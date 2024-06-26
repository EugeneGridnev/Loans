package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.navigation.ActivityRouter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class LoanAcceptedViewModel @AssistedInject constructor(
    @Assisted private val loanAmount: Int,
    private val activityRouter: ActivityRouter,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(loanAmount: Int): LoanAcceptedViewModel
    }

    val loanAmountValue = loanAmount

    fun openBanksStub() {
        activityRouter.openBanksStubScreen()
    }

    fun backToMain() {
        activityRouter.backToMain()
    }
}