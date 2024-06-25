package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class LoanAcceptedViewModel @AssistedInject constructor(
    @Assisted private val loanAmount: Int,
    private val activityRouter: ActivityRouter,
    private val mainFragmentRouter: MainFragmentRouter
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(loanAmount: Int): LoanAcceptedViewModel
    }

    fun openBanksStub() {
        activityRouter.openBanksStubScreen()
    }

    fun backToMain() {
        mainFragmentRouter.back()
        activityRouter.backToMain()
    }
}