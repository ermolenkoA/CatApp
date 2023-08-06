package com.example.catapp.remote.network

import com.example.catapp.remote.data.CatModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiInterface {
    @GET(".")
    suspend fun getCats(
        @Query("limit") amount: Int,
        @Query("page") page: Int,
        @Query("api_key") key: String = "live_e6rbpuwWo8pKad7dJATpQ6uD3I26h0v53XEkU5nim1ppRNPfIhFnt7odK5P0Ex2y"
    ): Response<List<CatModel>?>
}