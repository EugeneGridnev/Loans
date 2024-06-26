package com.shift.shiftfinal.navigation

import com.shift.shiftfinal.domain.entity.LoanConditionEntity

interface MainFragmentRouter {

    fun openHomeScreen()

    fun openMenuScreen()

    fun openMyLoansScreen()

    fun openLoanDetailsScreen(loanId: Int)

    fun openLoanApplicationScreen(loanCondition: LoanConditionEntity)

    fun back()

    fun backToHome()
}