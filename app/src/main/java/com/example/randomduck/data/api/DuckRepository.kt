package com.example.randomduck.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DuckApi {
    @GET("api/v2/duck/{id}")
    suspend fun fetchDuck(@Path("id") id: Int): DuckDetailResponse

    @GET("api/v2/duck/{name}")
    suspend fun fetchDuck(@Path("name") name: String): DuckDetailResponse

    @GET("api/v2/duck")
    suspend fun fetchAllDuck(): DuckListResponse
}

class DuckRepository private constructor(private val api:DuckApi) {
    private var _duck = MutableLiveData<DuckListApiModel>()
    val pokemon: LiveData<DuckListApiModel>
        get() = _duck

    companion object {
        private var _INSTANCE: DuckRepository? = null
        fun getInstance(): DuckRepository {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://random-d.uk/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val duckApi = retrofit.create(DuckApi::class.java)
            _INSTANCE = _INSTANCE ?: DuckRepository(duckApi)
            return _INSTANCE!!
        }
    }

    private fun mapDuck(duckResponse: DuckDetailResponse): DuckApiModel =
        DuckApiModel(
            duckResponse.id,
            duckResponse.sprites.frontDefault
        )

    suspend fun fetchOnlyOneDuck(id: Int): DuckApiModel = mapDuck(api.fetchDuck(id))

    suspend fun fetchOnlyOneDuck(name: String): DuckApiModel = mapDuck(api.fetchDuck(name))

    suspend fun fetchList() {
        val duckListResponse = api.fetchAllDuck()
        val duckList = duckListResponse.results.map {
            fetchOnlyOneDuck(it.name)
        }
        val duckListApiModel = DuckListApiModel(duckList)
        _duck.value = duckListApiModel
    }
}