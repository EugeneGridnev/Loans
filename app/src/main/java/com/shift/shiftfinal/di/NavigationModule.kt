package com.shift.shiftfinal.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.shift.shiftfinal.presentation.navigation.LoginRouter
import com.shift.shiftfinal.presentation.navigation.LoginRouterImpl
import com.shift.shiftfinal.presentation.navigation.SplashRouter
import com.shift.shiftfinal.presentation.navigation.SplashRouterImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class NavigationModule {


    companion object {

        private val cicerone: Cicerone<Router> = create()

        @Provides
        @Singleton
        fun provideRouter(): Router {
            return cicerone.router
        }

        @Provides
        @Singleton
        fun provideNavigatorHolder(): NavigatorHolder {
            return cicerone.getNavigatorHolder()
        }
    }

    @Binds
    @Singleton
    abstract fun provideSplashRouter(
        splashRouter: SplashRouterImpl
    ) : SplashRouter

    @Binds
    @Singleton
    abstract fun provideLoginRouter(
        loginRouter: LoginRouterImpl
    ) : LoginRouter
}