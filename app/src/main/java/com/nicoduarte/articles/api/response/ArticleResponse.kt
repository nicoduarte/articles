package com.nicoduarte.articles.api.response

import com.google.gson.annotations.SerializedName
import com.nicoduarte.articles.database.model.Article

data class ArticleResponse(
    @SerializedName("hits") val hits : List<Article>,
    @SerializedName("nbHits") val nbHits : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("nbPages") val nbPages : Int,
    @SerializedName("hitsPerPage") val hitsPerPage : Int,
    @SerializedName("exhaustiveNbHits") val exhaustiveNbHits : Boolean,
    @SerializedName("query") val query : String,
    @SerializedName("params") val params : String,
    @SerializedName("processingTimeMS") val processingTimeMS : Int
)