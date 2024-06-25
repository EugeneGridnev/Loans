package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val activityRouter: ActivityRouter,
    private val mainFragmentRouter: MainFragmentRouter
) : ViewModel() {

    fun openOnboarding() {
        activityRouter.openOnboardingFromMain()
    }

    fun openLoanApplication(loanCondition: LoanConditionEntity) {
        mainFragmentRouter.openLoanApplicationScreen(loanCondition)
    }
}