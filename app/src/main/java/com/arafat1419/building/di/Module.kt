package com.arafat1419.building.di

import com.arafat1419.building.core.domain.usecase.DataInteractor
import com.arafat1419.building.core.domain.usecase.DataUseCase
import com.arafat1419.building.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<DataUseCase> { DataInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}