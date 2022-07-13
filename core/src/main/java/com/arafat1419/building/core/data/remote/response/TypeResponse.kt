package com.arafat1419.building.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TypeResponse(

    @field:SerializedName("PR")
    val pr: String? = null,

    @field:SerializedName("BD")
    val bd: String? = null,

    @field:SerializedName("FL")
    val fl: String? = null,

    @field:SerializedName("RO")
    val ro: String? = null
)
