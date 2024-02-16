package com.example.newsapi.Model

import com.google.gson.annotations.SerializedName

data class Countries(
    var id : Int,
    var countryName: String,
    var countryCode : String
)
{
    override fun toString(): String {
        return countryCode
    }
}

