package com.tkdev.salsospontanapp.module

import app.cash.sqldelight.db.SqlDriver
import com.tkdev.salsospontanapp.database.SpontanDatabase
import com.tkdev.salsospontanapp.domain.DatabaseDriverFactory
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.data.SqlDelightArtistDataSource
import com.tkdev.salsospontanapp.ui.artists.AndroidArtistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SqlDriver> { DatabaseDriverFactory(get()).create() }
    single<ArtistDataSource> { SqlDelightArtistDataSource(SpontanDatabase(get())) }
    viewModel { AndroidArtistViewModel(get()) }
}
