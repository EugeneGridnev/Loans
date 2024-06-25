package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shift.shiftfinal.navigation.MainFragmentRouter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class LoanDetailsViewModel @AssistedInject constructor(
    @Assisted private val loanId: Int,
    private val mainFragmentRouter: MainFragmentRouter
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(loanId: Int): LoanDetailsViewModel
    }

    fun back() {
        mainFragmentRouter.back()
    }
}