package com.tkdev.salsospontanapp.android

import android.app.Application
import com.tkdev.salsospontanapp.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            listOf(
                modules(
                    module { single { this@MainApplication } },
                    appModule
                )
            )
        }
    }
}
