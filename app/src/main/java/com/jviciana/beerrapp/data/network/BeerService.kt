package com.jviciana.beerrapp.data.network

import android.util.Log
import com.jviciana.beerrapp.core.RetrofitHelper
import com.jviciana.beerrapp.data.model.BeerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BeerService @Inject constructor(
    private val retrofit: BeerApiClient
){
    suspend fun getBeersByName(name:String):List<BeerModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.getBeersByName(name)
            response.body() ?: emptyList()
        }
    }
}