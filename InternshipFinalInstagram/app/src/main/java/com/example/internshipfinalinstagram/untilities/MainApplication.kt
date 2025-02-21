package com.example.internshipfinalinstagram.untilities

import android.app.Application
import com.example.internshipfinalinstagram.di.repositoryModule
import com.example.internshipfinalinstagram.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}