package com.tkdev.salsospontanapp.domain

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.tkdev.salsospontanapp.database.SpontanDatabase

actual class DatabaseDriverFactory {

    actual fun create(): SqlDriver {
        return NativeSqliteDriver(SpontanDatabase.Schema, "timetable.db")
    }
}
