package com.jviciana.beerrapp.domain

import com.jviciana.beerrapp.data.repository.BeerRepository
import com.jviciana.beerrapp.data.model.BeerModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class GetBeersByName @AssistedInject constructor(
    @Assisted val name: String,
    private val repository: BeerRepository) {

    suspend operator fun invoke():List<BeerModel>? = repository.getBeersByName(name)

}

