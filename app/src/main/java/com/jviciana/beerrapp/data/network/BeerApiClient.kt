package com.jviciana.beerrapp.data.network

import com.jviciana.beerrapp.data.model.BeerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BeerApiClient {
    @GET("getBeers.php")
    suspend fun getBeersByName(@Query("beer_name") name: String): Response<List<BeerModel>>
}