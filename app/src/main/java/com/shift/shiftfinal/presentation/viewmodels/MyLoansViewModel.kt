package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.navigation.MainFragmentRouter
import javax.inject.Inject

class MyLoansViewModel @Inject constructor(
    private val mainFragmentRouter: MainFragmentRouter
) : ViewModel() {

    fun openLoanDetails(loanId: Int) {
        mainFragmentRouter.openLoanDetailsScreen(loanId)
    }

    fun back() {
        mainFragmentRouter.back()
    }
}