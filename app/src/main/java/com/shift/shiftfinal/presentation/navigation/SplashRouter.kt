package com.shift.shiftfinal.presentation.navigation

import com.shift.shiftfinal.domain.entity.LoanConditionEntity

interface SplashRouter {

    fun openLoginScreen()

    fun openHomeScreen(loanCondition: LoanConditionEntity)
}