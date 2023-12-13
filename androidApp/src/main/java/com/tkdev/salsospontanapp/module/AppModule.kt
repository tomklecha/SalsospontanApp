package com.tkdev.salsospontanapp.module

import com.tkdev.salsospontanapp.database.SpontanDatabase
import com.tkdev.salsospontanapp.domain.DatabaseDriverFactory
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.artists.SqlDelightArtistDataSource
import com.tkdev.salsospontanapp.domain.venues.SqlDelightVenuesDataSource
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
import com.tkdev.salsospontanapp.domain.workshops.SqlDelightWorkshopsDataSource
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource
import com.tkdev.salsospontanapp.remote.HttpClientFactory
import com.tkdev.salsospontanapp.remote.KtorRemoteClient
import com.tkdev.salsospontanapp.remote.RemoteClient
import com.tkdev.salsospontanapp.ui.artists.AndroidArtistViewModel
import com.tkdev.salsospontanapp.ui.splash.AndroidSplashViewModel
import com.tkdev.salsospontanapp.ui.venues.AndroidVenuesViewModel
import com.tkdev.salsospontanapp.ui.workshops.AndroidWorkshopsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { DatabaseDriverFactory(get()).create() }
    single { HttpClientFactory().create() }

    // Data Sources
    single<ArtistDataSource> { SqlDelightArtistDataSource(SpontanDatabase(get())) }
    single<VenuesDataSource> { SqlDelightVenuesDataSource(SpontanDatabase(get())) }
    single<WorkshopsDataSource> { SqlDelightWorkshopsDataSource(SpontanDatabase(get())) }
    single<RemoteClient> { KtorRemoteClient(get()) }

    // View Models
    viewModel { AndroidArtistViewModel(get()) }
    viewModel { AndroidVenuesViewModel(get()) }
    viewModel { AndroidWorkshopsViewModel(get(), get(), get()) }
    viewModel { AndroidSplashViewModel(get(), get(), get(), get()) }
}
