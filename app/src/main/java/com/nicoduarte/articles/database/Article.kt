package com.nicoduarte.articles.database

import com.google.gson.annotations.SerializedName

data class Article (
	@SerializedName("created_at") val createdAt : String,
	@SerializedName("title") val title : String,
	@SerializedName("url") val url : String,
	@SerializedName("author") val author : String,
	@SerializedName("points") val points : String,
	@SerializedName("story_text") val storyText : String,
	@SerializedName("comment_text") val commentText : String,
	@SerializedName("num_comments") val numComments : String,
	@SerializedName("story_id") val storyId : Int,
	@SerializedName("story_title") val storyTitle : String?,
	@SerializedName("story_url") val storyUrl : String,
	@SerializedName("parent_id") val parentId : Int,
	@SerializedName("created_at_i") val createdAtI : Int,
	@SerializedName("_tags") val tags : List<String>,
	@SerializedName("objectID") val objectID : Int,
	@SerializedName("_highlightResult") val highlightResult : HighlightResult
)