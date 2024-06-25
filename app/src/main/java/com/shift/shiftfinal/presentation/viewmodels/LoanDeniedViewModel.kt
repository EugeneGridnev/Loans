package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import javax.inject.Inject

class LoanDeniedViewModel @Inject constructor(
    private val activityRouter: ActivityRouter,
    private val mainFragmentRouter: MainFragmentRouter
) : ViewModel() {

    fun backToMain() {
        mainFragmentRouter.back()
        activityRouter.backToMain()
    }

}