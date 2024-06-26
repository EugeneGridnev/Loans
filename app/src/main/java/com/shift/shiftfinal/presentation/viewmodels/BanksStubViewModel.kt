package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import dagger.assisted.AssistedInject
import javax.inject.Inject

class BanksStubViewModel @Inject constructor(
    private val activityRouter: ActivityRouter,
    private val mainFragmentRouter: MainFragmentRouter
) : ViewModel() {


    fun backToMain() {
        mainFragmentRouter.back()
        activityRouter.backToMain()
    }


}