package com.jviciana.beerrapp.data.model

import com.google.gson.annotations.SerializedName

data class BeerModel (
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("description") val description:String,
    @SerializedName("image_url") val image:String,
    @SerializedName("abv") val abv:Double)
