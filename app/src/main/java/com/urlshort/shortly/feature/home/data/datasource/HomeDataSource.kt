package com.urlshort.shortly.feature.home.data.datasource

import com.urlshort.shortly.base.model.data.DataResult
import com.urlshort.shortly.feature.home.model.HomeDataModel

interface HomeDataSource {
    suspend fun shortenUrl(url: String): DataResult<HomeDataModel>
}