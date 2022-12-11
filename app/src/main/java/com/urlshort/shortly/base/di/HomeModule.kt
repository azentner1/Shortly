package com.urlshort.shortly.base.di

import com.urlshort.shortly.feature.home.data.api.HomeApiService
import com.urlshort.shortly.feature.home.data.datasource.HomeDataSource
import com.urlshort.shortly.feature.home.data.datasource.HomeDataSourceImpl
import com.urlshort.shortly.feature.home.data.mapper.HomeDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun providesHomeDataSource(
        homeApiService: HomeApiService,
        homeDataMapper: HomeDataMapper
    ): HomeDataSource =
        HomeDataSourceImpl(homeApiService, homeDataMapper)
}

