package com.shift.shiftfinal.ui.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.ui.HomeFragment
import com.shift.shiftfinal.ui.LoginFragment
import com.shift.shiftfinal.ui.SplashFragment

fun getSplashScreen() = FragmentScreen { SplashFragment() }

fun getLoginScreen() = FragmentScreen { LoginFragment() }

fun getHomeScreen(loanCondition: LoanConditionEntity) =
    FragmentScreen { HomeFragment.newInstance(loanCondition) }