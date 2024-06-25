package com.shift.shiftfinal.navigation

import com.shift.shiftfinal.domain.entity.LoanConditionEntity

interface ActivityRouter {

    fun openLoginScreen()

    fun openMainScreen(loanCondition: LoanConditionEntity)

    fun openOnboarding(loanCondition: LoanConditionEntity)

    fun openLoanAcceptedScreen()

    fun openLoanDeniedScreen()

    fun openBanksStubScreen()

    fun openHelpScreen()

    fun openSpecialOfferScreen()
}