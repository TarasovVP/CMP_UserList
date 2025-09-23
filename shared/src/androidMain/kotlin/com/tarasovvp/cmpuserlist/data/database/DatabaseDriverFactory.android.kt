package com.tarasovvp.cmpuserlist.data.database

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.tarasovvp.cmpuserlist.Constants
import com.tarasovvp.cmpuserlist.db.UserDatabase

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
actual class DatabaseDriverFactory(private val context: Context) {
    actual suspend fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = UserDatabase.Schema.synchronous(),
            context,
            Constants.USER_DB,
        )
    }
}