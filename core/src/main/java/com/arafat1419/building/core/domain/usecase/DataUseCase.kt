package com.arafat1419.building.core.domain.usecase

import com.arafat1419.building.core.domain.domain.CreateDomain
import com.arafat1419.building.core.domain.domain.LocationDomain
import com.arafat1419.building.core.domain.domain.TypeDomain
import com.arafat1419.building.core.domain.domain.UpdateDomain
import com.arafat1419.building.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface DataUseCase {
    fun getLocations(): Flow<Resource<List<LocationDomain>>>
    fun getLocationById(locationId: String): Flow<Resource<LocationDomain>>
    fun getType(): Flow<Resource<TypeDomain>>
    fun getProjects(): Flow<Resource<List<LocationDomain>>>
    fun getBuildingByProject(projectCode: String): Flow<Resource<List<LocationDomain>>>
    fun getFloorsByBuilding(buildingCode: String): Flow<Resource<List<LocationDomain>>>
    fun getRoomsByFloor(floorCode: String): Flow<Resource<List<LocationDomain>>>

    fun createLocation(createDomain: CreateDomain): Flow<Resource<String>>
    fun updateLocation(locationId: String, updateDomain: UpdateDomain): Flow<Resource<String>>

}