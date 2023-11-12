package com.tkdev.salsospontanapp.domain

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.tkdev.salsospontanapp.database.SpontanDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(SpontanDatabase.Schema, context, "spontanDatabase.db")
    }
}
