package com.arafat1419.building.core.domain.usecase

import com.arafat1419.building.core.domain.domain.CreateDomain
import com.arafat1419.building.core.domain.domain.LocationDomain
import com.arafat1419.building.core.domain.domain.TypeDomain
import com.arafat1419.building.core.domain.domain.UpdateDomain
import com.arafat1419.building.core.domain.repository.IDataRepository
import com.arafat1419.building.core.utils.DataMapper
import com.arafat1419.building.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class DataInteractor(private val iDataRepository: IDataRepository) : DataUseCase {
    override fun getLocations(): Flow<Resource<List<LocationDomain>>> =
        iDataRepository.getLocations()

    override fun getLocationById(locationId: String): Flow<Resource<LocationDomain>> =
        iDataRepository.getLocationById(locationId)

    override fun getType(): Flow<Resource<TypeDomain>> =
        iDataRepository.getType()

    override fun getProjects(): Flow<Resource<List<LocationDomain>>> =
        iDataRepository.getProjects()

    override fun getBuildingByProject(projectCode: String): Flow<Resource<List<LocationDomain>>> =
        iDataRepository.getBuildingByProject(projectCode)

    override fun getFloorsByBuilding(buildingCode: String): Flow<Resource<List<LocationDomain>>> =
        iDataRepository.getFloorsByBuilding(buildingCode)

    override fun getRoomsByFloor(floorCode: String): Flow<Resource<List<LocationDomain>>> =
        iDataRepository.getRoomsByFloor(floorCode)

    override fun createLocation(createDomain: CreateDomain): Flow<Resource<String>> =
        iDataRepository.createLocation(
            DataMapper.createDomainToResponse(createDomain)
        )

    override fun updateLocation(
        locationId: String,
        updateDomain: UpdateDomain
    ): Flow<Resource<String>> =
        iDataRepository.updateLocation(
            locationId,
            DataMapper.updateDomainToResponse(updateDomain)
        )
}