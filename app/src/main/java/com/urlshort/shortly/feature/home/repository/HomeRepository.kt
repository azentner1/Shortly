package com.urlshort.shortly.feature.home.repository

import com.urlshort.shortly.base.model.data.UiDataState
import com.urlshort.shortly.base.model.domain.ShortenData
import com.urlshort.shortly.base.repository.BaseRepository
import com.urlshort.shortly.feature.home.data.dao.HomeDao
import com.urlshort.shortly.feature.home.data.datasource.HomeDataSource
import com.urlshort.shortly.feature.home.data.mapper.HomeDataMapper
import com.urlshort.shortly.feature.home.model.HomeDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeDataSource: HomeDataSource,
    private val homeDataMapper: HomeDataMapper,
    private val homeDao: HomeDao
): BaseRepository() {

    suspend fun shortenUrl(url: String): Flow<UiDataState<HomeDataModel>> {
        return fetchData(
            remote = { homeDataSource.shortenUrl(url) },
            persistData = { data ->
                homeDao.saveUrl(homeDataMapper.mapToRealmEntity(data))
            }
        )
    }

    suspend fun observeShortenedUrls(): Flow<List<ShortenData>> {
        return homeDao.observeUrls().map {
            homeDataMapper.mapFromRealmEntity(it)
        }
    }

}