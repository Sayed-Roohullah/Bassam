package com.acclivousbyte.bassam.backend

import androidx.multidex.MultiDexApplication
import com.acclivousbyte.bassam.koinDI.appModule
import com.acclivousbyte.bassam.koinDI.repoModule
import com.acclivousbyte.bassam.koinDI.utilModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, utilModule))
        }
    }
}