package com.example.app.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ArticleEntity(
    val title: String,
    var source: String,
    @Json(name = "time")
    var timestamp: String
)

data class ArticleResponse(val data: List<ArticleEntity>): BaseResponse()
