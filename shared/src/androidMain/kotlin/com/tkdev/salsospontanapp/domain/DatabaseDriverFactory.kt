package com.tkdev.salsospontanapp.domain

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver


actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        TODO() return AndroidSqliteDriver(SpontanDatabase.Schema, context, "translate.db")
    }
}
