package com.example.warningapp.data.model

import com.example.warningapp.utilities.Severity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WarningResponseItem(
    @Json(name = "i18nTitle")
    val i18nTitle: I18nTitle,
    @Json(name = "id")
    val id: String,
    @Json(name = "severity")
    val severity: Severity,
    @Json(name = "startDate")
    val startDate: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "version")
    val version: Int
)
