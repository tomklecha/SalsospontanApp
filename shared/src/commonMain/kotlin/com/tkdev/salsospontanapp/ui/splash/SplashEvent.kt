package com.tkdev.salsospontanapp.ui.splash

sealed class SplashEvent {

    data object PrepopulateData : SplashEvent()
    data object LoadingComplete : SplashEvent()
    data object OnError : SplashEvent()
}
