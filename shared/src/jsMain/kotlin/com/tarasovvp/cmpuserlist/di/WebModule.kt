package com.tarasovvp.cmpuserlist.di

import com.tarasovvp.cmpuserlist.data.database.DatabaseDriverFactory
import org.koin.dsl.module

val webModule =
    module {
        single {
            DatabaseDriverFactory()
        }
    }
