package com.example.randomduck.data.api

data class DuckApiModel(
    val id: Int,
    val frontUrl: String?
)

data class DuckListApiModel(
    val list: List<DuckApiModel>
)