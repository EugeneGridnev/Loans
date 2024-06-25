package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val activityRouter: ActivityRouter,
    private val mainFragmentRouter: MainFragmentRouter
) : ViewModel() {

    fun openOnboarding() {
        activityRouter.openOnboardingFromMain()
    }

    fun openMyLoans() {
        mainFragmentRouter.openMyLoansScreen()
    }

    fun openSpecialOffer() {
        activityRouter.openSpecialOfferScreen()
    }

    fun openBanksStub() {
        activityRouter.openBanksStubScreen()
    }

    fun openHelp() {
        activityRouter.openHelpScreen()
    }
}