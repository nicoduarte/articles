package com.nicoduarte.articles.database

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author (
	@SerializedName("value") val value : String,
	@SerializedName("matchLevel") val matchLevel : String,
	@SerializedName("matchedWords") val matchedWords : List<String>
) : Parcelable