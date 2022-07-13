package com.arafat1419.building.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @field:SerializedName("locID")
    val locID: String? = null,
    @field:SerializedName("locCode")
    val locCode: String? = null,
    @field:SerializedName("locName")
    val locName: String? = null,
    @field:SerializedName("locType")
    val locType: String? = null,
    @field:SerializedName("locActive")
    val locActive: Boolean? = null,
    @field:SerializedName("locTypeLabel")
    val locTypeLabel: String? = null,
    @field:SerializedName("project")
    val project: LocationResponse? = null,
    @field:SerializedName("building")
    val building: LocationResponse? = null,
    @field:SerializedName("floor")
    val floor: LocationResponse? = null,
)
