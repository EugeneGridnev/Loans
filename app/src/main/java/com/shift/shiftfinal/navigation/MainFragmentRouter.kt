package com.shift.shiftfinal.navigation

import com.shift.shiftfinal.domain.entity.LoanApplicationEntity

interface MainFragmentRouter {

    fun openHomeScreen()

    fun openMenuScreen()

    fun openMyLoansScreen()

    fun openLoanDetailsScreen(loanId: Int)

    fun openLoanApplicationScreen(loanApplication: LoanApplicationEntity)

    fun back()

    fun backToHome()
}