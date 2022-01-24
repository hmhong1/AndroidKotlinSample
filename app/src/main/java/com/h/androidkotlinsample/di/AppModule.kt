package com.h.androidkotlinsample.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * app 사용시 사용될 모듈(hilt)
 */
@Module
@InstallIn(ActivityComponent::class)
class AppModule {

//    @Provides
//    @Singleton
//    fun provideSharedPreference(): Config {
//        return Config()
//    }
}