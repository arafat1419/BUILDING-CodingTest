package com.arafat1419.building

import android.app.Application
import com.arafat1419.building.core.di.networkModule
import com.arafat1419.building.core.di.repositoryModule
import com.arafat1419.building.di.useCaseModule
import com.arafat1419.building.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApp)

            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}