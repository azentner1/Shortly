package com.urlshort.shortly.feature.home.data.mapper

import com.urlshort.shortly.base.mapper.EntityMapper
import com.urlshort.shortly.base.model.domain.ShortenData
import com.urlshort.shortly.base.model.realm.HomeDataRealm
import com.urlshort.shortly.feature.home.model.HomeDataModel
import com.urlshort.shortly.feature.home.model.HomeResponse
import io.realm.RealmResults
import javax.inject.Inject

class HomeDataMapper @Inject constructor() : EntityMapper<HomeResponse?, HomeDataModel> {

    override fun mapFromEntity(entity: HomeResponse?): HomeDataModel {
        return HomeDataModel(
            ShortenData(
                shortLink = entity?.result?.shortLink ?: "",
                fullShortLink = entity?.result?.fullShortLink ?: "",
                originalLink = entity?.result?.originalLink ?: ""
            )
        )
    }

    override fun mapToEntity(model: HomeDataModel): HomeResponse {
        TODO("Not yet implemented")
    }

    fun mapToRealmEntity(data: HomeDataModel): HomeDataRealm {
        return HomeDataRealm().apply {
            shortLink = data.shortenData.shortLink
            fullShortLink = data.shortenData.fullShortLink
            originalLink = data.shortenData.originalLink
        }
    }

    fun mapFromRealmEntity(realmList: RealmResults<HomeDataRealm>): List<ShortenData> {
        return realmList.map {
            ShortenData(
                shortLink = it.shortLink,
                fullShortLink = it.fullShortLink,
                originalLink = it.originalLink
            )
        }
    }
}