package com.arafat1419.building.core.utils

import com.arafat1419.building.core.data.remote.response.CreateResponse
import com.arafat1419.building.core.data.remote.response.LocationResponse
import com.arafat1419.building.core.data.remote.response.TypeResponse
import com.arafat1419.building.core.data.remote.response.UpdateResponse
import com.arafat1419.building.core.domain.domain.CreateDomain
import com.arafat1419.building.core.domain.domain.LocationDomain
import com.arafat1419.building.core.domain.domain.TypeDomain
import com.arafat1419.building.core.domain.domain.UpdateDomain

object DataMapper {

    fun locationResponseToDomain(input: LocationResponse): LocationDomain =
        LocationDomain(
            input.locID,
            input.locCode,
            input.locName,
            input.locType,
            input.locActive,
            input.locTypeLabel,
            LocationDomain(
                input.project?.locID,
                input.project?.locCode,
                input.project?.locName,
                input.project?.locType,
                input.project?.locActive
            ),
            LocationDomain(
                input.building?.locID,
                input.building?.locCode,
                input.building?.locName,
                input.building?.locType,
                input.building?.locActive
            ),
            LocationDomain(
                input.floor?.locID,
                input.floor?.locCode,
                input.floor?.locName,
                input.floor?.locType,
                input.floor?.locActive
            )
        )

    fun typeResponseToDomain(input: TypeResponse): TypeDomain =
        TypeDomain(
            input.pr,
            input.bd,
            input.fl,
            input.ro
        )

    fun createDomainToResponse(input: CreateDomain): CreateResponse =
        CreateResponse(
            input.locName,
            input.locType,
            input.projectCode,
            input.buildingCode,
            input.floorCode
        )

    fun updateDomainToResponse(input: UpdateDomain): UpdateResponse =
        UpdateResponse(
            input.locName,
            input.locType
        )
}