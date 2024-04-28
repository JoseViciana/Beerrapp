package com.jviciana.beerrapp.data.model

import com.jviciana.beerrapp.domain.GetBeersByName
import dagger.assisted.AssistedFactory

@AssistedFactory
interface BeerFactory {
    fun create(name:String):GetBeersByName
}