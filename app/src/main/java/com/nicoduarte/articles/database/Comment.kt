package com.nicoduarte.articles.database

import com.google.gson.annotations.SerializedName

data class Comment (
	@SerializedName("value") val value : String,
	@SerializedName("matchLevel") val matchLevel : String,
	@SerializedName("fullyHighlighted") val fullyHighlighted : Boolean,
	@SerializedName("matchedWords") val matchedWords : List<String>
)