package com.example.newsapi.Model

data class NewsResponse(
    var status : String,
    var totalResults : String,
    var articles : MutableList<ArticleList>
)
