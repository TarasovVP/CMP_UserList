package com.tarasovvp.cmpuserlist

import android.app.Application
import com.tarasovvp.cmpuserlist.di.initKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}