package com.example.randomduck.data.api

import com.google.gson.annotations.SerializedName

data class DuckListResponse(
    val duckResponse: List<DuckApiModel>
)

data class DuckDetailResponse(
    val image : DuckTypeResponse
)
 data class DuckTypeResponse(
     @SerializedName("randomimg")
     val random : String
 )