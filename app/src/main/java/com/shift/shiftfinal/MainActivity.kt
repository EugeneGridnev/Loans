package com.shift.shiftfinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.shift.shiftfinal.di.AppComponent
import javax.inject.Inject
import javax.inject.Named
import com.shift.shiftfinal.ui.screens.getSplashScreen as getLoanSplashScreen

class MainActivity : AppCompatActivity() {

    @Inject
    @Named("ActivityRouter")
    lateinit var router: Router
    @Inject
    @Named("ActivityHolder")
    lateinit var navigatorHolder: NavigatorHolder

    lateinit var appComponent: AppComponent

    private val navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as App).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            router.newRootScreen(getLoanSplashScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}