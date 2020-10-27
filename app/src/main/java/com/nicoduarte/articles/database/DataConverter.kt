package com.nicoduarte.articles.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun fromTagList(tagList: List<String>): String? {
        if (tagList.isNullOrEmpty()) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Int>?>() {}.type
        return gson.toJson(tagList, type)
    }

    @TypeConverter
    fun toTagList(tags: String?): List<String> {
        if (tags == null) {
            return emptyList()
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>?>() {}.type
        return gson.fromJson(tags, type)
    }

}