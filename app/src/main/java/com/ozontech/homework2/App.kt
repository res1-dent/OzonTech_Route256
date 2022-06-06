package com.ozontech.homework2

import android.app.Application
import com.ozontech.homework2.di.ServiceLocator

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(this)
    }
}