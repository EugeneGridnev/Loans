package com.shift.shiftfinal.navigation

import com.github.terrakok.cicerone.Router
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.ui.screens.getHomeScreen
import com.shift.shiftfinal.ui.screens.getLoanApplicationScreen
import com.shift.shiftfinal.ui.screens.getLoanDetailsScreen
import com.shift.shiftfinal.ui.screens.getMenuScreen
import com.shift.shiftfinal.ui.screens.getMyLoansScreen
import javax.inject.Inject
import javax.inject.Named

class MainFragmentRouterImpl @Inject constructor(
    @Named("MainFragmentRouter") private val mainFragmentRouter: Router
) : MainFragmentRouter {
    override fun openHomeScreen() {
        mainFragmentRouter.replaceScreen(getHomeScreen())
    }

    override fun openMenuScreen() {
        mainFragmentRouter.replaceScreen(getMenuScreen())
    }

    override fun openMyLoansScreen() {
        mainFragmentRouter.replaceScreen(getMyLoansScreen())
    }

    override fun openLoanDetailsScreen(loanId: Int) {
        mainFragmentRouter.replaceScreen(getLoanDetailsScreen(loanId))
    }

    override fun openLoanApplicationScreen(loanCondition: LoanConditionEntity) {
        mainFragmentRouter.navigateTo(getLoanApplicationScreen(loanCondition))
    }

    override fun back() {
        mainFragmentRouter.exit()
    }
}