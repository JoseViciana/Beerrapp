package com.jviciana.beerrapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerProvider @Inject constructor(){
        var beers: List<BeerModel> = emptyList()
}