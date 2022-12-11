package com.urlshort.shortly.feature.home.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("ok") val isOk: Boolean,
    @SerializedName("result") val result: ResultResponse,
)

data class ResultResponse(
    @SerializedName("code") val code: String,
    @SerializedName("short_link") val shortLink: String,
    @SerializedName("full_short_link") val fullShortLink: String,
    @SerializedName("short_link2") val shortLinkAlternate: String,
    @SerializedName("full_short_link2") val fullShortLinkAlternate: String,
    @SerializedName("share_link") val shareLink: String,
    @SerializedName("full_share_link") val fullShareLink: String,
    @SerializedName("original_link") val originalLink: String
)