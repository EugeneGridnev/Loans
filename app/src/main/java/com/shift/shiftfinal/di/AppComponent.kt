package com.shift.shiftfinal.di

import android.content.Context
import com.shift.shiftfinal.MainActivity
import com.shift.shiftfinal.ui.SplashFragment
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

    fun inject(fragment: SplashFragment)
    fun inject(fragment: MainActivity)
}