package com.shift.shiftfinal.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.ActivityRouterImpl
import com.shift.shiftfinal.navigation.MainFragmentRouter
import com.shift.shiftfinal.navigation.MainFragmentRouterImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class NavigationModule {


    companion object {

        private val ciceroneActivity: Cicerone<Router> = create()
        private val ciceroneMainFragment: Cicerone<Router> = create()

        @Provides
        @Singleton
        @Named("ActivityRouter")
        fun provideActivityRouter(): Router {
            return ciceroneActivity.router
        }

        @Provides
        @Singleton
        @Named("ActivityHolder")
        fun provideActivityNavigatorHolder(): NavigatorHolder {
            return ciceroneActivity.getNavigatorHolder()
        }

        @Provides
        @Singleton
        @Named("MainFragmentRouter")
        fun provideMainFragmentRouter(): Router {
            return ciceroneMainFragment.router
        }

        @Provides
        @Singleton
        @Named("MainFragmentHolder")
        fun provideMainFragmentNavigatorHolder(): NavigatorHolder {
            return ciceroneMainFragment.getNavigatorHolder()
        }
    }

    @Binds
    @Singleton
    abstract fun provideActivityRouter(
        activityRouter: ActivityRouterImpl
    ) : ActivityRouter

    @Binds
    @Singleton
    abstract fun provideMainFragmentRouter(
        mainFragmentRouter: MainFragmentRouterImpl
    ) : MainFragmentRouter

}