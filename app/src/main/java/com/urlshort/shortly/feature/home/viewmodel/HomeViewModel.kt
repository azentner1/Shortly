package com.urlshort.shortly.feature.home.viewmodel

import android.webkit.URLUtil
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.urlshort.shortly.base.model.data.UiDataState
import com.urlshort.shortly.base.model.domain.ShortenData
import com.urlshort.shortly.base.viewmodel.BaseViewModel
import com.urlshort.shortly.feature.home.model.HomeDataModel
import com.urlshort.shortly.feature.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Math.ceil
import java.util.*
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel<HomeDataModel, HomeDataEvent>() {

    var shortUrlList: List<ShortenData> by mutableStateOf(listOf())
    var copiedUrl by mutableStateOf("")
    var inputError by mutableStateOf("")
    var timeout by mutableStateOf(3)

    override suspend fun doActionForEvent(event: HomeDataEvent) {
        when(event) {
            is HomeDataEvent.ShortenUrl -> {
                saveShortenedUrl(event.url)
            }
            is HomeDataEvent.FetchShortenedUrls -> {
                observeShortenedUrls()
            }
        }
    }

    private suspend fun saveShortenedUrl(url: String) {
        if (!validateUrl(url)) {
            return
        }
        homeRepository.shortenUrl(url).collect {
            dataState = it
            if (it is UiDataState.Error) {
                inputError = "Something went wrong. Please try again."
            }
        }
    }

    private fun observeShortenedUrls() {
        viewModelScope.launch {
            homeRepository.observeShortenedUrls().collect {
                shortUrlList = it
            }
        }
    }

    private fun validateUrl(url: String): Boolean {
        return when {
            !URLUtil.isValidUrl(url) -> {
                inputError = "That's not a valid url."
                false
            }
            url.isEmpty() -> {
                inputError = "Enter an url."
                false
            }
            else -> {
                true
            }
        }
    }

    fun copyUrl(shortLink: String) {
        copiedUrl = shortLink
    }

    fun getSecondsLeft(until: Date): Int {
        return ceil((until.time - Date().time) / 360_000.0).toInt()
    }
}

sealed class HomeDataEvent {
    data class ShortenUrl(val url: String): HomeDataEvent()
    object FetchShortenedUrls: HomeDataEvent()
}