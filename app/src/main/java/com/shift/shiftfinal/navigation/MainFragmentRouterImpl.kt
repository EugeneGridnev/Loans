package com.shift.shiftfinal.navigation

import com.github.terrakok.cicerone.Router
import com.shift.shiftfinal.domain.entity.LoanApplicationEntity
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
        mainFragmentRouter.navigateTo(getMyLoansScreen())
    }

    override fun openLoanDetailsScreen(loanId: Int) {
        mainFragmentRouter.navigateTo(getLoanDetailsScreen(loanId))
    }

    override fun openLoanApplicationScreen(loanApplication: LoanApplicationEntity) {
        mainFragmentRouter.navigateTo(getLoanApplicationScreen(loanApplication))
    }

    override fun back() {
        mainFragmentRouter.exit()
    }

    override fun backToHome() {
        mainFragmentRouter.backTo(getHomeScreen())
    }
}