package com.rrdev.dbccompany

import android.app.Application
import com.rrdev.di.dataModule
import com.rrdev.di.dataRemoteModule
import com.rrdev.di.domainModule
import com.rrdev.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    dataModule,
                    dataRemoteModule,
                    domainModule,
                    presentationModule
                )
            ).androidContext(applicationContext)
        }
    }
}