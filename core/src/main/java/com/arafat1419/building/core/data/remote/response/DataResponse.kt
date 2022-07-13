package com.arafat1419.building.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse<T>(
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: T
)
