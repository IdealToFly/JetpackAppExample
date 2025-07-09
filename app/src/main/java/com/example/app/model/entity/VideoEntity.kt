package com.example.app.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoEntity(
    val title: String,
    val type: String,
    val duration: String,
    @Json(name = "cover")
    val imageUrl: Int
)
