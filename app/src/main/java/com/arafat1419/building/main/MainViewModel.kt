package com.arafat1419.building.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arafat1419.building.core.domain.domain.CreateDomain
import com.arafat1419.building.core.domain.domain.UpdateDomain
import com.arafat1419.building.core.domain.usecase.DataUseCase

class MainViewModel(private val dataUseCase: DataUseCase) : ViewModel() {
    fun getLocations() = dataUseCase.getLocations().asLiveData()
    fun getLocationById(locationId: String) = dataUseCase.getLocationById(locationId).asLiveData()
    fun getType() = dataUseCase.getType().asLiveData()
    fun getProjects() = dataUseCase.getProjects().asLiveData()
    fun getBuildingByProject(projectCode: String) =
        dataUseCase.getBuildingByProject(projectCode).asLiveData()

    fun getFloorsByBuilding(buildingCode: String) =
        dataUseCase.getFloorsByBuilding(buildingCode).asLiveData()

    fun getRoomsByFloor(floorCode: String) =
        dataUseCase.getRoomsByFloor(floorCode).asLiveData()

    fun createLocation(createDomain: CreateDomain) =
        dataUseCase.createLocation(createDomain).asLiveData()

    fun updateLocation(locationId: String, updateDomain: UpdateDomain) =
        dataUseCase.updateLocation(locationId, updateDomain).asLiveData()
}