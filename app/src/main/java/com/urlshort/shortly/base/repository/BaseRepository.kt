package com.urlshort.shortly.base.repository

import com.urlshort.shortly.base.model.data.DataResult
import com.urlshort.shortly.base.model.data.ErrorEntity
import com.urlshort.shortly.base.model.data.UiDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform

abstract class BaseRepository {

    private suspend fun <T> fetchRemoteData(
        remote: suspend () -> DataResult<T>
    ): DataResult<T> {
        return remote()
    }

    suspend fun <T> fetchData(
        remote: suspend () -> DataResult<T>,
        persistData: suspend (T) -> Unit
    ): Flow<UiDataState<T>> {
        val dataFlow = flow {
            emit(UiDataState.Loading)
        }
        val remoteData = fetchRemoteData(remote)
        return if(remoteData is DataResult.Success) {
            dataFlow.flowOn(Dispatchers.IO).onEach {
                persistData.invoke(remoteData.data)
            }.flowOn(Dispatchers.Default).transform {
                emit(UiDataState.Success(remoteData.data))
            }
        } else {
            dataFlow.transform {
                emit(UiDataState.Error(ErrorEntity.Unknown))
            }
        }
    }
}