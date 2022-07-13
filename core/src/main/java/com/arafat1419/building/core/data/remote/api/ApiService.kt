package com.arafat1419.building.core.data.remote.api

import com.arafat1419.building.core.data.remote.response.*
import retrofit2.http.*

interface ApiService {
    @GET("locations")
    suspend fun getLocations(): ListResponse<LocationResponse>

    @GET("locations/{location_id}")
    suspend fun getLocationById(
        @Path("location_id") locationId: String
    ): DataResponse<LocationResponse>

    @GET("locations/type")
    suspend fun getType(): DataResponse<TypeResponse>

    @GET("locations/project")
    suspend fun getProjects(): ListResponse<LocationResponse>

    @GET("locations/building/{project_code}")
    suspend fun getBuildingByProject(
        @Path("project_code") projectCode: String
    ): ListResponse<LocationResponse>

    @GET("locations/floor/{building_code}")
    suspend fun getFloorsByBuilding(
        @Path("building_code") buildingCode: String
    ): ListResponse<LocationResponse>

    @GET("locations/room/{floor_code}")
    suspend fun getRoomsByFloor(
        @Path("floor_code") floorCode: String
    ): ListResponse<LocationResponse>

    @POST("locations")
    suspend fun createLocation(
        @Body createResponse: CreateResponse
    ): MessageResponse

    @PUT("locations/{location_id}")
    suspend fun updateLocation(
        @Path("location_id") locationId: String,
        @Body updateResponse: UpdateResponse
    ): MessageResponse
}