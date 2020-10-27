package com.nicoduarte.articles.database.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HighlightResult (
	@SerializedName("author") val author : Author,
	@SerializedName("comment_text") val commentText : Comment,
	@SerializedName("story_title") val storyTitle : StoryTitle,
	@SerializedName("story_url") val storyUrl : StoryUrl
) : Parcelable