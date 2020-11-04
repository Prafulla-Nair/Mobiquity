package com.example.sampleapplication.di

import com.example.sampleapplication.model.CategoriesApi
import com.example.sampleapplication.model.CategoriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val baseUrl = "http://mobcategories.s3-website-eu-west-1.amazonaws.com"

    @Provides
    fun provideCategoriesApi(): CategoriesApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CategoriesApi::class.java)
    }

    @Provides
    fun provideCategoriesService(): CategoriesService {
        return CategoriesService()
    }
}
