package com.shift.shiftfinal.navigation

import com.github.terrakok.cicerone.Router
import com.shift.shiftfinal.ui.screens.getLoginScreen
import com.shift.shiftfinal.ui.screens.getMainScreen
import com.shift.shiftfinal.ui.screens.getOnBoardingScreen
import javax.inject.Inject
import javax.inject.Named

class ActivityRouterImpl @Inject constructor(
    @Named("ActivityRouter") private val activityRouter: Router
) : ActivityRouter {
    override fun openLoginScreen() {
        activityRouter.replaceScreen(getLoginScreen())
    }

    override fun openMainScreen() {
        activityRouter.replaceScreen(getMainScreen())
    }

    override fun openOnboarding() {
        activityRouter.replaceScreen(getOnBoardingScreen())
    }

    override fun openOnboardingFromMain() {
        activityRouter.navigateTo(getOnBoardingScreen())
    }

    override fun openLoanAcceptedScreen() {
        TODO("Not yet implemented")
    }

    override fun openLoanDeniedScreen() {
        TODO("Not yet implemented")
    }

    override fun openBanksStubScreen() {
        TODO("Not yet implemented")
    }

    override fun openHelpScreen() {
        TODO("Not yet implemented")
    }

    override fun openSpecialOfferScreen() {
        TODO("Not yet implemented")
    }

    override fun backToMain() {
        activityRouter.backTo(getMainScreen())
    }

}

