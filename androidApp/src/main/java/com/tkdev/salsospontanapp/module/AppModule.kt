package com.tkdev.salsospontanapp.module

import app.cash.sqldelight.db.SqlDriver
import com.tkdev.salsospontanapp.database.SpontanDatabase
import com.tkdev.salsospontanapp.domain.DatabaseDriverFactory
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.data.SqlDelightArtistDataSource
import com.tkdev.salsospontanapp.domain.data.SqlDelightVenuesDataSource
import com.tkdev.salsospontanapp.domain.data.SqlDelightWorkshopsDataSource
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource
import com.tkdev.salsospontanapp.ui.artists.AndroidArtistViewModel
import com.tkdev.salsospontanapp.ui.venues.AndroidVenuesViewModel
import com.tkdev.salsospontanapp.ui.workshops.AndroidWorkshopsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<SqlDriver> { DatabaseDriverFactory(get()).create() }

    // Data Sources
    single<ArtistDataSource> { SqlDelightArtistDataSource(SpontanDatabase(get())) }
    single<VenuesDataSource> { SqlDelightVenuesDataSource(SpontanDatabase(get())) }
    single<WorkshopsDataSource> { SqlDelightWorkshopsDataSource(SpontanDatabase(get())) }

    // View Models
    viewModel { AndroidArtistViewModel(get()) }
    viewModel { AndroidVenuesViewModel(get()) }
    viewModel { AndroidWorkshopsViewModel(get()) }
}
