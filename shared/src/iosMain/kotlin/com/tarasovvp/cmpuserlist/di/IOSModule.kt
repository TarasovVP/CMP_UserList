package com.tarasovvp.cmpuserlist.di

import com.tarasovvp.cmpuserlist.data.database.DatabaseDriverFactory
import org.koin.dsl.module

val iosModule =
    module {
        single {
            DatabaseDriverFactory()
        }
    }
