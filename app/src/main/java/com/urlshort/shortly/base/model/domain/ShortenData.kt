package com.urlshort.shortly.base.model.domain

data class ShortenData(
    val shortLink: String,
    val fullShortLink: String,
    val originalLink: String
)