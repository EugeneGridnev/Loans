package com.shift.shiftfinal.presentation.navigation

import com.github.terrakok.cicerone.Router
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.ui.screens.getHomeScreen
import com.shift.shiftfinal.ui.screens.getLoginScreen
import javax.inject.Inject

class SplashRouterImpl @Inject constructor(
    private val router: Router
) : SplashRouter {
    override fun openLoginScreen() {
        router.replaceScreen(getLoginScreen())
    }

    override fun openHomeScreen(loanCondition: LoanConditionEntity) {
        router.replaceScreen(getHomeScreen(loanCondition))
    }
}

