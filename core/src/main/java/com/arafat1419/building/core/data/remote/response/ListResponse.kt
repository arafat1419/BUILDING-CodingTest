package com.arafat1419.building.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val result: List<T>? = null
)