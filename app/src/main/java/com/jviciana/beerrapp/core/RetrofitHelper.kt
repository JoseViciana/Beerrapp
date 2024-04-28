package com.jviciana.beerrapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.jviciana.com/beers/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}