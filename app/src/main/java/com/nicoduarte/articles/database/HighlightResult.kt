package com.nicoduarte.articles.database

import com.google.gson.annotations.SerializedName

data class HighlightResult (
	@SerializedName("author") val author : Author,
	@SerializedName("comment_text") val commentText : Comment,
	@SerializedName("story_title") val storyTitle : StoryTitle,
	@SerializedName("story_url") val storyUrl : StoryUrl
)