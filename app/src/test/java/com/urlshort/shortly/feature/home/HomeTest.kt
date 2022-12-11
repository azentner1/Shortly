package com.urlshort.shortly.feature.home

import com.google.common.truth.Truth.assertThat
import com.urlshort.shortly.feature.home.repository.HomeRepository
import com.urlshort.shortly.feature.home.viewmodel.HomeViewModel
import io.mockk.mockk
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.Test

@InternalCoroutinesApi
class HomeTest {

    lateinit var viewModel: HomeViewModel
    lateinit var repository: HomeRepository

    @Before
    fun setUp() {
        repository = mockk<HomeRepository>()
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun validateUrls() {
        assertThat(viewModel.validateUrl("  ")).isFalse()
        assertThat(viewModel.validateUrl("www.google.com")).isTrue()
        assertThat(viewModel.validateUrl("https://www.google.com")).isTrue()
        assertThat(viewModel.validateUrl("wwwgooglecom")).isFalse()
    }
}