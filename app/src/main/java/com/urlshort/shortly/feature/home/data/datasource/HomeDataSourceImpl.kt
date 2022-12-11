package com.urlshort.shortly.feature.home.data.datasource

import com.urlshort.shortly.base.model.data.DataResult
import com.urlshort.shortly.base.model.data.ErrorEntity
import com.urlshort.shortly.feature.home.data.api.HomeApiService
import com.urlshort.shortly.feature.home.data.mapper.HomeDataMapper
import com.urlshort.shortly.feature.home.model.HomeDataModel
import com.urlshort.shortly.feature.home.model.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HomeDataSourceImpl(
    private val homeApiService: HomeApiService,
    private val homeDataMapper: HomeDataMapper
) : HomeDataSource {

    override suspend fun shortenUrl(url: String): DataResult<HomeDataModel> {
        return suspendCoroutine {
            homeApiService.shortenUrl(url).enqueue(object : Callback<HomeResponse> {
                override fun onResponse(
                    call: Call<HomeResponse>,
                    response: Response<HomeResponse>
                ) {
                    if (response.isSuccessful) {
                        it.resume(DataResult.Success(homeDataMapper.mapFromEntity(response.body())))
                    } else {
                        it.resume(DataResult.Error(ErrorEntity.Network))
                    }
                }

                override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                    it.resume(DataResult.Error(ErrorEntity.Network))
                }
            })
        }
    }
}