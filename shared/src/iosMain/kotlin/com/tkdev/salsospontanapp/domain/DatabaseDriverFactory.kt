package com.tkdev.salsospontanapp.domain

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.tkdev.salsospontanapp.database.SpontanDatabase

actual class DatabaseDriverFactory {

    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            schema = SpontanDatabase.Schema,
            name = "timetable.db",
            onConfiguration = configuration()
        )
    }

    private fun configuration(): (DatabaseConfiguration) -> DatabaseConfiguration = { config ->
        config.copy(
            extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
        )
    }
}
