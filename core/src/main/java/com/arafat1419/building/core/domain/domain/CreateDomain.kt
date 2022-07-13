package com.arafat1419.building.core.domain.domain

data class CreateDomain(
    var locName: String? = null,
    var locType: String? = null,
    var projectCode: String? = null,
    var buildingCode: String? = null,
    var floorCode: String? = null
)
