package com.shift.shiftfinal.navigation

import com.github.terrakok.cicerone.Router
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.ui.screens.getHomeScreen
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

    override fun openMainScreen(loanCondition: LoanConditionEntity) {
        activityRouter.replaceScreen(getMainScreen(loanCondition))
    }

    override fun openOnboarding(loanCondition: LoanConditionEntity) {
        activityRouter.replaceScreen(getOnBoardingScreen(loanCondition))
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


}

