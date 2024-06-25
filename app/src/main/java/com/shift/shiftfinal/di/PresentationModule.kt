package com.shift.shiftfinal.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shift.shiftfinal.presentation.ViewModelFactory
import com.shift.shiftfinal.presentation.viewmodels.BanksStubViewModel
import com.shift.shiftfinal.presentation.viewmodels.HelpViewModel
import com.shift.shiftfinal.presentation.viewmodels.HomeViewModel
import com.shift.shiftfinal.presentation.viewmodels.LoanDeniedViewModel
import com.shift.shiftfinal.presentation.viewmodels.LoginViewModel
import com.shift.shiftfinal.presentation.viewmodels.MenuViewModel
import com.shift.shiftfinal.presentation.viewmodels.MyLoansViewModel
import com.shift.shiftfinal.presentation.viewmodels.OnBoardingViewModel
import com.shift.shiftfinal.presentation.viewmodels.SpecialOfferViewModel
import com.shift.shiftfinal.presentation.viewmodels.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {


    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewmodel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnBoardingViewModel::class)
    fun bindOnBoardingViewModel(viewmodel: OnBoardingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewmodel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    fun bindMenuViewModel(viewmodel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewmodel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BanksStubViewModel::class)
    fun bindBanksStubViewModel(viewmodel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoanDeniedViewModel::class)
    fun bindLoanDeniedViewModel(viewmodel: LoanDeniedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyLoansViewModel::class)
    fun bindMyLoansViewModel(viewmodel: MyLoansViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HelpViewModel::class)
    fun bindHelpViewModel(viewmodel: HelpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SpecialOfferViewModel::class)
    fun bindSpecialOfferViewModel(viewmodel: SpecialOfferViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}