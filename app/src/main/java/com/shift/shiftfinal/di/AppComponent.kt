package com.shift.shiftfinal.di

import android.content.Context
import com.shift.shiftfinal.MainActivity
import com.shift.shiftfinal.ui.BanksStubFragment
import com.shift.shiftfinal.ui.HelpFragment
import com.shift.shiftfinal.ui.HomeFragment
import com.shift.shiftfinal.ui.LoanAcceptedFragment
import com.shift.shiftfinal.ui.LoanApplicationFragment
import com.shift.shiftfinal.ui.LoanDeniedFragment
import com.shift.shiftfinal.ui.LoginFragment
import com.shift.shiftfinal.ui.MainFragment
import com.shift.shiftfinal.ui.MenuFragment
import com.shift.shiftfinal.ui.MyLoansFragment
import com.shift.shiftfinal.ui.SpecialOfferFragment
import com.shift.shiftfinal.ui.SplashFragment
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class,
        DataModule::class,
        PresentationModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent

    }

    fun inject(fragment: MainFragment)
    fun inject(fragment: SplashFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: MenuFragment)
    fun inject(fragment: LoanDeniedFragment)
    fun inject(fragment: MyLoansFragment)
    fun inject(fragment: SpecialOfferFragment)
    fun inject(fragment: LoanAcceptedFragment)
    fun inject(fragment: LoanApplicationFragment)
    fun inject(fragment: HelpFragment)
    fun inject(fragment: OnBoardingFragment)
    fun inject(fragment: BanksStubFragment)
    fun inject(fragment: MainActivity)
}