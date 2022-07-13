package com.arafat1419.building.core.domain.domain

data class LocationDomain(
    val locID: String? = null,
    val locCode: String? = null,
    val locName: String? = null,
    val locType: String? = null,
    val locActive: Boolean? = null,
    val locTypeLabel: String? = null,
    val project: LocationDomain? = null,
    val building: LocationDomain? = null,
    val floor: LocationDomain? = null,
)
