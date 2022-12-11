package com.urlshort.shortly.feature.home.data.dao

import com.urlshort.shortly.base.database.RealmDB
import com.urlshort.shortly.base.model.realm.HomeDataRealm
import io.realm.RealmResults
import io.realm.kotlin.toFlow
import kotlinx.coroutines.flow.Flow

class HomeDaoImpl : HomeDao {

    override suspend fun saveUrl(homeDataRealm: HomeDataRealm) {
        RealmDB.realmInstance().executeTransaction {
            it.insertOrUpdate(homeDataRealm)
        }
    }

    override suspend fun observeUrls(): Flow<RealmResults<HomeDataRealm>> {
        return RealmDB.realmInstance().where(HomeDataRealm::class.java).findAll().toFlow()
    }

    override suspend fun deleteUrl(url: String) {
        val urlData =
            RealmDB.realmInstance().where(HomeDataRealm::class.java).equalTo("shortLink", url)
                .findFirst()
        RealmDB.realmInstance().executeTransaction {
            urlData?.deleteFromRealm()
        }
    }
}