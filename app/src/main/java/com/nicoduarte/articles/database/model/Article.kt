package com.nicoduarte.articles.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "article_table")
@Parcelize
data class Article (
	@ColumnInfo(name = "created_at")
	@SerializedName("created_at") val createdAt : String,
	@ColumnInfo(name = "title")
	@SerializedName("title") val title : String?,
	@ColumnInfo(name = "url")
	@SerializedName("url") val url : String?,
	@ColumnInfo(name = "author")
	@SerializedName("author") val author : String,
	@ColumnInfo(name = "points")
	@SerializedName("points") val points : Int?,
	@ColumnInfo(name = "story_text")
	@SerializedName("story_text") val storyText : String?,
	@ColumnInfo(name = "comment_text")
	@SerializedName("comment_text") val commentText : String?,
	@ColumnInfo(name = "num_comments")
	@SerializedName("num_comments") val numComments : Int?,
	@ColumnInfo(name = "story_id")
	@SerializedName("story_id") val storyId : Int,
	@ColumnInfo(name = "story_title")
	@SerializedName("story_title") val storyTitle : String?,
	@ColumnInfo(name = "story_url")
	@SerializedName("story_url") val storyUrl : String?,
	@ColumnInfo(name = "parent_id")
	@SerializedName("parent_id") val parentId : Int,
	@ColumnInfo(name = "created_at_i")
	@SerializedName("created_at_i") val createdAtI : Int,
	@PrimaryKey
	@ColumnInfo(name = "id")
	@SerializedName("objectID") val id : String
) : Parcelable