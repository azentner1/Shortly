package com.urlshort.shortly.feature.home.data.dao

import com.urlshort.shortly.base.model.realm.HomeDataRealm
import io.realm.RealmResults
import kotlinx.coroutines.flow.Flow

interface HomeDao {
    suspend fun saveUrl(homeDataRealm: HomeDataRealm)
    suspend fun observeUrls(): Flow<RealmResults<HomeDataRealm>>
}