package com.example.newsapi.Model

import com.google.gson.annotations.SerializedName

data class ArticleList(
    var source: Source,
    var author : String,
    var title : String,
    var description : String,
    var url : String,
    @SerializedName("urlToImage")
    var image : String,
    var publishedAt : String,
    var content : String
)
