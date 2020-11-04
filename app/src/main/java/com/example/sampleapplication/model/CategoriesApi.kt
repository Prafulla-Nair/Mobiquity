package com.example.sampleapplication.model

import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesApi {

    @GET("http://mobcategories.s3-website-eu-west-1.amazonaws.com")
    fun getCategories(): Single<List<Category>>
}
