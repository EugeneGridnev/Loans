package com.shift.shiftfinal.navigation

interface ActivityRouter {

    fun openLoginScreen()

    fun openMainScreen()

    fun openOnboarding()

    fun openOnboardingFromMain()

    fun openLoanAcceptedScreen(loanAmount: Int)

    fun openLoanDeniedScreen()

    fun openBanksStubScreen()

    fun openHelpScreen()

    fun openSpecialOfferScreen()

    fun backToMain()
}