package com.example.randomduck.data.api

import com.google.gson.annotations.SerializedName

data class DuckListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<DuckListItemResponse>
)

data class DuckListItemResponse(
    val name:String
)

data class DuckDetailResponse(
    val id:Int,
    val sprites: DuckSpritesResponse
)

data class DuckSpritesResponse(
    @SerializedName("front_default")
    val frontDefault:String
)
