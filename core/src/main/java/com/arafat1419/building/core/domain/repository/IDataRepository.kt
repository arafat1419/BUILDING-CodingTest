package com.arafat1419.building.core.domain.repository

import com.arafat1419.building.core.data.remote.response.CreateResponse
import com.arafat1419.building.core.data.remote.response.UpdateResponse
import com.arafat1419.building.core.domain.domain.LocationDomain
import com.arafat1419.building.core.domain.domain.TypeDomain
import com.arafat1419.building.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IDataRepository {
    fun getLocations(): Flow<Resource<List<LocationDomain>>>
    fun getLocationById(locationId: String): Flow<Resource<LocationDomain>>
    fun getType(): Flow<Resource<TypeDomain>>
    fun getProjects(): Flow<Resource<List<LocationDomain>>>
    fun getBuildingByProject(projectCode: String): Flow<Resource<List<LocationDomain>>>
    fun getFloorsByBuilding(buildingCode: String): Flow<Resource<List<LocationDomain>>>
    fun getRoomsByFloor(floorCode: String): Flow<Resource<List<LocationDomain>>>

    fun createLocation(createResponse: CreateResponse): Flow<Resource<String>>
    fun updateLocation(locationId: String, updateResponse: UpdateResponse): Flow<Resource<String>>
}