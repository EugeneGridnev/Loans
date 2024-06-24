package com.shift.shiftfinal

import android.app.Application
import com.shift.shiftfinal.di.AppComponent
import com.shift.shiftfinal.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
    }
}