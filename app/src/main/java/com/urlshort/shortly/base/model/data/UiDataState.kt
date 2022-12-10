package com.urlshort.shortly.base.model.data

sealed class UiDataState<out T> {

    data class Success<out T>(val data: T) : UiDataState<T>()

    data class Error(val error: ErrorEntity) : UiDataState<Nothing>()

    object Loading : UiDataState<Nothing>()
}