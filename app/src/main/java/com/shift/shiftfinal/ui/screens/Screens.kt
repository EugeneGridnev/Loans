package com.shift.shiftfinal.ui.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.ui.BanksStubFragment
import com.shift.shiftfinal.ui.HelpFragment
import com.shift.shiftfinal.ui.HomeFragment
import com.shift.shiftfinal.ui.LoanAcceptedFragment
import com.shift.shiftfinal.ui.LoanApplicationFragment
import com.shift.shiftfinal.ui.LoanDeniedFragment
import com.shift.shiftfinal.ui.LoanDetailsFragment
import com.shift.shiftfinal.ui.LoginFragment
import com.shift.shiftfinal.ui.MainFragment
import com.shift.shiftfinal.ui.MenuFragment
import com.shift.shiftfinal.ui.MyLoansFragment
import com.shift.shiftfinal.ui.SpecialOfferFragment
import com.shift.shiftfinal.ui.SplashFragment
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingFragment

fun getSplashScreen() = FragmentScreen { SplashFragment() }

fun getOnBoardingScreen() = FragmentScreen { OnBoardingFragment() }

fun getLoginScreen() = FragmentScreen { LoginFragment() }

fun getMainScreen() = FragmentScreen { MainFragment() }

fun getHomeScreen() = FragmentScreen { HomeFragment() }

fun getLoanApplicationScreen(loanCondition: LoanConditionEntity) =
    FragmentScreen { LoanApplicationFragment.newInstance(loanCondition) }

fun getLoanAcceptedScreen(loanAmount: Int) =
    FragmentScreen { LoanAcceptedFragment.newInstance(loanAmount) }

fun getLoanDeniedScreen() = FragmentScreen { LoanDeniedFragment() }

fun getBanksStubScreen() = FragmentScreen { BanksStubFragment() }

fun getMenuScreen() = FragmentScreen { MenuFragment() }

fun getMyLoansScreen() = FragmentScreen { MyLoansFragment() }

fun getLoanDetailsScreen(loanId: Int) = FragmentScreen { LoanDetailsFragment.newInstance(loanId) }

fun getHelpScreen() = FragmentScreen { HelpFragment() }

fun getSpecialOfferScreen() = FragmentScreen { SpecialOfferFragment() }
