package com.nicoduarte.articles.database.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment (
	@SerializedName("value") val value : String,
	@SerializedName("matchLevel") val matchLevel : String,
	@SerializedName("fullyHighlighted") val fullyHighlighted : Boolean,
	@SerializedName("matchedWords") val matchedWords : List<String>
) : Parcelable