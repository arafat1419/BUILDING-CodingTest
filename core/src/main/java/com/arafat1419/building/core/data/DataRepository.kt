package com.arafat1419.building.core.data

import com.arafat1419.building.core.data.remote.RemoteDataSource
import com.arafat1419.building.core.data.remote.api.ApiResponse
import com.arafat1419.building.core.data.remote.response.CreateResponse
import com.arafat1419.building.core.data.remote.response.LocationResponse
import com.arafat1419.building.core.data.remote.response.TypeResponse
import com.arafat1419.building.core.data.remote.response.UpdateResponse
import com.arafat1419.building.core.domain.domain.LocationDomain
import com.arafat1419.building.core.domain.domain.TypeDomain
import com.arafat1419.building.core.domain.repository.IDataRepository
import com.arafat1419.building.core.utils.DataMapper
import com.arafat1419.building.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class DataRepository(
    private val remoteDataSource: RemoteDataSource
) : IDataRepository {

    override fun getLocations(): Flow<Resource<List<LocationDomain>>> =
        object : NetworkBoundResource<List<LocationDomain>, List<LocationResponse>>() {
            override suspend fun load(data: List<LocationResponse>): Flow<List<LocationDomain>> {
                return listOf(data.map {
                    DataMapper.locationResponseToDomain(it)
                }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<LocationResponse>>> {
                return remoteDataSource.getLocations()
            }

        }.asFlow()

    override fun getLocationById(locationId: String): Flow<Resource<LocationDomain>> =
        object : NetworkBoundResource<LocationDomain, LocationResponse>() {
            override suspend fun load(data: LocationResponse): Flow<LocationDomain> {
                return listOf(DataMapper.locationResponseToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<LocationResponse>> {
                return remoteDataSource.getLocationById(locationId)
            }

        }.asFlow()

    override fun getType(): Flow<Resource<TypeDomain>> =
        object : NetworkBoundResource<TypeDomain, TypeResponse>() {
            override suspend fun load(data: TypeResponse): Flow<TypeDomain> {
                return listOf(DataMapper.typeResponseToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<TypeResponse>> {
                return remoteDataSource.getType()
            }

        }.asFlow()

    override fun getProjects(): Flow<Resource<List<LocationDomain>>> =
        object : NetworkBoundResource<List<LocationDomain>, List<LocationResponse>>() {
            override suspend fun load(data: List<LocationResponse>): Flow<List<LocationDomain>> {
                return listOf(data.map {
                    DataMapper.locationResponseToDomain(it)
                }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<LocationResponse>>> {
                return remoteDataSource.getProjects()
            }

        }.asFlow()

    override fun getBuildingByProject(projectCode: String): Flow<Resource<List<LocationDomain>>> =
        object : NetworkBoundResource<List<LocationDomain>, List<LocationResponse>>() {
            override suspend fun load(data: List<LocationResponse>): Flow<List<LocationDomain>> {
                return listOf(data.map {
                    DataMapper.locationResponseToDomain(it)
                }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<LocationResponse>>> {
                return remoteDataSource.getBuildingByProject(projectCode)
            }

        }.asFlow()

    override fun getFloorsByBuilding(buildingCode: String): Flow<Resource<List<LocationDomain>>> =
        object : NetworkBoundResource<List<LocationDomain>, List<LocationResponse>>() {
            override suspend fun load(data: List<LocationResponse>): Flow<List<LocationDomain>> {
                return listOf(data.map {
                    DataMapper.locationResponseToDomain(it)
                }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<LocationResponse>>> {
                return remoteDataSource.getFloorsByBuilding(buildingCode)
            }

        }.asFlow()

    override fun getRoomsByFloor(floorCode: String): Flow<Resource<List<LocationDomain>>> =
        object : NetworkBoundResource<List<LocationDomain>, List<LocationResponse>>() {
            override suspend fun load(data: List<LocationResponse>): Flow<List<LocationDomain>> {
                return listOf(data.map {
                    DataMapper.locationResponseToDomain(it)
                }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<LocationResponse>>> {
                return remoteDataSource.getRoomsByFloor(floorCode)
            }

        }.asFlow()

    override fun createLocation(createResponse: CreateResponse): Flow<Resource<String>> =
        object : NetworkBoundResource<String, String>() {
            override suspend fun load(data: String): Flow<String> {
                return listOf(data).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<String>> {
                return remoteDataSource.createLocation(createResponse)
            }
        }.asFlow()

    override fun updateLocation(
        locationId: String,
        updateResponse: UpdateResponse
    ): Flow<Resource<String>> =
        object : NetworkBoundResource<String, String>() {
            override suspend fun load(data: String): Flow<String> {
                return listOf(data).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<String>> {
                return remoteDataSource.updateLocation(locationId, updateResponse)
            }
        }.asFlow()
}