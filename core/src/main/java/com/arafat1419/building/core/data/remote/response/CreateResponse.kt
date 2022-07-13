package com.arafat1419.building.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreateResponse(
    @field:SerializedName("locName")
    var locName: String? = null,
    @field:SerializedName("locType")
    var locType: String? = null,
    @field:SerializedName("projectCode")
    var projectCode: String? = null,
    @field:SerializedName("buildingCode")
    var buildingCode: String? = null,
    @field:SerializedName("floorCode")
    var floorCode: String? = null
)
