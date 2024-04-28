package com.jviciana.beerrapp.data.repository

import com.jviciana.beerrapp.data.model.BeerModel
import com.jviciana.beerrapp.data.model.BeerProvider
import com.jviciana.beerrapp.data.network.BeerService
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val api: BeerService,
    private val beerProvider: BeerProvider
){
    suspend fun getBeersByName(name: String):List<BeerModel>{
        val response = api.getBeersByName(name)
        beerProvider.beers = response
        return response
    }
}