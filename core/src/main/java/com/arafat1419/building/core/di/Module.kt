package com.arafat1419.building.core.di

import com.arafat1419.building.core.BuildConfig.BASE_URL
import com.arafat1419.building.core.data.DataRepository
import com.arafat1419.building.core.data.remote.RemoteDataSource
import com.arafat1419.building.core.data.remote.api.ApiService
import com.arafat1419.building.core.domain.repository.IDataRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<IDataRepository> {
        DataRepository(get())
    }
}