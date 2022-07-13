package com.arafat1419.building.core.data.remote

import com.arafat1419.building.core.data.remote.api.ApiResponse
import com.arafat1419.building.core.data.remote.api.ApiService
import com.arafat1419.building.core.data.remote.response.CreateResponse
import com.arafat1419.building.core.data.remote.response.LocationResponse
import com.arafat1419.building.core.data.remote.response.TypeResponse
import com.arafat1419.building.core.data.remote.response.UpdateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getLocations(): Flow<ApiResponse<List<LocationResponse>>> =
        flow {
            try {

                val response = apiService.getLocations()
                val listResponse = response.result
                if (listResponse != null) {
                    if (listResponse.isNotEmpty()) {
                        emit(ApiResponse.Success(response.result))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getLocationById(locationId: String): Flow<ApiResponse<LocationResponse>> =
        flow {
            try {
                val response = apiService.getLocationById(locationId)

                emit(ApiResponse.Success(response.data))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getType(): Flow<ApiResponse<TypeResponse>> =
        flow {
            try {
                val response = apiService.getType()

                emit(ApiResponse.Success(response.data))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getProjects(): Flow<ApiResponse<List<LocationResponse>>> =
        flow {
            try {

                val response = apiService.getProjects()
                val listResponse = response.result
                if (listResponse != null) {
                    if (listResponse.isNotEmpty()) {
                        emit(ApiResponse.Success(response.result))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getBuildingByProject(projectCode: String): Flow<ApiResponse<List<LocationResponse>>> =
        flow {
            try {

                val response = apiService.getBuildingByProject(projectCode)
                val listResponse = response.result
                if (listResponse != null) {
                    if (listResponse.isNotEmpty()) {
                        emit(ApiResponse.Success(response.result))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getFloorsByBuilding(buildingCode: String): Flow<ApiResponse<List<LocationResponse>>> =
        flow {
            try {

                val response = apiService.getFloorsByBuilding(buildingCode)
                val listResponse = response.result
                if (listResponse != null) {
                    if (listResponse.isNotEmpty()) {
                        emit(ApiResponse.Success(response.result))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getRoomsByFloor(floorCode: String): Flow<ApiResponse<List<LocationResponse>>> =
        flow {
            try {

                val response = apiService.getRoomsByFloor(floorCode)
                val listResponse = response.result
                if (listResponse != null) {
                    if (listResponse.isNotEmpty()) {
                        emit(ApiResponse.Success(response.result))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun createLocation(createResponse: CreateResponse): Flow<ApiResponse<String>> =
        flow {
            try {
                val response = apiService.createLocation(createResponse)

                emit(ApiResponse.Success(response.message))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun updateLocation(
        locationId: String,
        updateResponse: UpdateResponse
    ): Flow<ApiResponse<String>> =
        flow {
            try {
                val response = apiService.updateLocation(locationId, updateResponse)

                emit(ApiResponse.Success(response.message))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}