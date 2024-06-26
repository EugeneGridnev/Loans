package com.shift.shiftfinal.navigation

import com.github.terrakok.cicerone.Router
import com.shift.shiftfinal.ui.screens.getBanksStubScreen
import com.shift.shiftfinal.ui.screens.getHelpScreen
import com.shift.shiftfinal.ui.screens.getHomeScreen
import com.shift.shiftfinal.ui.screens.getLoanAcceptedScreen
import com.shift.shiftfinal.ui.screens.getLoanDeniedScreen
import com.shift.shiftfinal.ui.screens.getLoginScreen
import com.shift.shiftfinal.ui.screens.getMainScreen
import com.shift.shiftfinal.ui.screens.getOnBoardingScreen
import com.shift.shiftfinal.ui.screens.getSpecialOfferScreen
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

    override fun openLoanAcceptedScreen(loanAmount: Int) {
        activityRouter.navigateTo(getLoanAcceptedScreen(loanAmount))
    }

    override fun openLoanDeniedScreen() {
        activityRouter.navigateTo(getLoanDeniedScreen())
    }

    override fun openBanksStubScreen() {
        activityRouter.navigateTo(getBanksStubScreen())
    }

    override fun openHelpScreen() {
        activityRouter.navigateTo(getHelpScreen())
    }

    override fun openSpecialOfferScreen() {
        activityRouter.navigateTo(getSpecialOfferScreen())
    }

    override fun backToMain() {
        activityRouter.backTo(getHomeScreen())
    }

    override fun exitToLogin() {
        activityRouter.newRootChain(getLoginScreen())
    }

}

