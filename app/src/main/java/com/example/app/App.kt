package com.example.app

import android.app.Application
import com.example.di.appModule
import com.example.di.dataModule
import com.example.di.domainModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    appModule,
                    domainModule,
                    dataModule
                )
            )
        }
    }

}