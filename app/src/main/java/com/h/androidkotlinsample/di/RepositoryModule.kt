package com.h.androidkotlinsample.di

import com.h.androidkotlinsample.network.ApiServiceInterface
import com.h.androidkotlinsample.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * repository 관련 모듈(hilt)
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideNetworkRepository(retrofitApi : ApiServiceInterface): ApiRepository {
        return ApiRepository(retrofitApi)
    }
}