package com.tarasovvp.cmpuserlist.di

import com.tarasovvp.cmpuserlist.data.database.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule =
    module {
        single {
            DatabaseDriverFactory(androidContext())
        }
    }
