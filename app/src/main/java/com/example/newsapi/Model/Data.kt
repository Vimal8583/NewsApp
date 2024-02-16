package com.example.newsapi.Model

data class Data(
    var status: String,
    var totalResults: Int,
    var articles:MutableList<ArticleList>
)
