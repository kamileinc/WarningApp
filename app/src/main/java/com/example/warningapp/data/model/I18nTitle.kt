package com.example.warningapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Json(name = "i18nTitle")
data class I18nTitle(
    @Json(name = "de")
    val de: String? = "Unknown title",
)
