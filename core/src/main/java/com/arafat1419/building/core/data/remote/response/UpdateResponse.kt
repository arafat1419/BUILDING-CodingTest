package com.arafat1419.building.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateResponse(

    @field:SerializedName("locName")
    val locName: String? = null,

    @field:SerializedName("locType")
    val locType: String? = null
)
