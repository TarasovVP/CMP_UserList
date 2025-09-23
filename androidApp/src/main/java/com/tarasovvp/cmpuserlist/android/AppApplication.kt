package com.tarasovvp.cmpuserlist.android

import android.app.Application
import com.tarasovvp.cmpuserlist.di.initKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}