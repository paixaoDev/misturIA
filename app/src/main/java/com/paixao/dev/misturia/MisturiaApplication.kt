package com.paixao.dev.misturia

import android.app.Application
import com.paixao.dev.misturia.di.networkModule
import com.paixao.dev.misturia.di.repositoryModule
import com.paixao.dev.misturia.di.useCaseModule
import com.paixao.dev.misturia.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MisturiaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MisturiaApplication)
            modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}