package com.urlshort.shortly.base.di

import com.urlshort.shortly.feature.home.data.dao.HomeDao
import com.urlshort.shortly.feature.home.data.dao.HomeDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun providesAlbumDao(): HomeDao =
        HomeDaoImpl()
}