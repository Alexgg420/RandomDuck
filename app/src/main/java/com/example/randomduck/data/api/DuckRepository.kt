package com.example.randomduck.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DuckApi {
    @GET("api/v2/random")
    suspend fun fetchDuck(@Path("url") url: String): DuckResponse
}

class DuckRepository private constructor(private val api:DuckApi) {
    private val _duck = MutableLiveData<DuckResponse>()
    val duck: LiveData<DuckResponse>
        get() = _duck
    companion object {
        private var _INSTANCE: DuckRepository? = null
        fun getInstance(): DuckRepository {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://random-d.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val duckApi = retrofit.create(DuckApi::class.java)
            _INSTANCE = _INSTANCE ?: DuckRepository(duckApi)
            return _INSTANCE!!
        }
    }

    private fun mapDuck(duckResponse: DuckResponse): DuckResponse = DuckResponse(duckResponse.url)

    suspend fun fetchOnlyOneDuck(url: String) {
        val duckResponse = api.fetchDuck(url)
        _duck.value = mapDuck(duckResponse)
    }
}