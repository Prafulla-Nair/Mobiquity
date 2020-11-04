package com.example.sampleapplication.di

import com.example.sampleapplication.model.CategoriesService
import com.example.sampleapplication.viewmodel.SharedViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CategoriesService)

    fun inject(viewModel: SharedViewModel)
}
