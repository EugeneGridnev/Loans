package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.navigation.ActivityRouter
import javax.inject.Inject

class SpecialOfferViewModel @Inject constructor(
    private val activityRouter: ActivityRouter
) : ViewModel() {

    fun backToMain() {
        activityRouter.backToMain()
    }

    fun openBanksStub() {
        activityRouter.openBanksStubScreen()
    }
}