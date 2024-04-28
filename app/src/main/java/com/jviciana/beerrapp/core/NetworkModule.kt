package com.jviciana.beerrapp.core

import com.jviciana.beerrapp.data.network.BeerApiClient
import com.jviciana.beerrapp.data.repository.BeerRepository
import com.jviciana.beerrapp.domain.GetBeersByName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.jviciana.com/beers/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBeerApiClient(retrofit: Retrofit):BeerApiClient{
        return retrofit.create(BeerApiClient::class.java)
    }
}