package com.shift.shiftfinal.navigation

import com.shift.shiftfinal.domain.entity.LoanConditionEntity

interface ActivityRouter {

    fun openLoginScreen()

    fun openMainScreen()

    fun openOnboarding()

    fun openOnboardingFromMain()

    fun openLoanAcceptedScreen()

    fun openLoanDeniedScreen()

    fun openBanksStubScreen()

    fun openHelpScreen()

    fun openSpecialOfferScreen()

    fun backToMain()
}