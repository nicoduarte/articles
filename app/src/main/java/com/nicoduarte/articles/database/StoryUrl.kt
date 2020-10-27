package com.nicoduarte.articles.database

import com.google.gson.annotations.SerializedName

data class StoryUrl (
	@SerializedName("value") val value : String,
	@SerializedName("matchLevel") val matchLevel : String,
	@SerializedName("matchedWords") val matchedWords : List<String>
)