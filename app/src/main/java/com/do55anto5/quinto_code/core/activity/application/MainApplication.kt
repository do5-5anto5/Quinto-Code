package com.do55anto5.quinto_code.core.activity.application

import android.app.Application
import com.do55anto5.quinto_code.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(repositoryModule)
        }
    }
}