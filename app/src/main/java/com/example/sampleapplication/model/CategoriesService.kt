package com.example.sampleapplication.model

import com.example.sampleapplication.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CategoriesService {

    @Inject
    lateinit var api: CategoriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCategories(): Single<List<Category>> {
        return api.getCategories()
    }
}
