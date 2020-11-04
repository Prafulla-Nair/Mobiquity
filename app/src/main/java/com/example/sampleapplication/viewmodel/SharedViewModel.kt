package com.example.sampleapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapplication.di.DaggerApiComponent
import com.example.sampleapplication.model.CategoriesService
import com.example.sampleapplication.model.Category
import com.example.sampleapplication.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SharedViewModel : ViewModel() {

    @Inject
    lateinit var categoriesService: CategoriesService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val categories = MutableLiveData<List<Category>>()
    val categoriesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val selectedProduct = MutableLiveData<Product>()
    val selectedCategory = MutableLiveData<Category>()

    fun fetchCategories() {
        loading.value = true
        disposable.add(
            categoriesService.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Category>>() {
                    override fun onSuccess(t: List<Category>) {
                        categories.value = t
                        categoriesLoadError.value = false
                        loading.value = false
                    }
                    override fun onError(e: Throwable) {
                        categoriesLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    fun setProduct(product: Product) {
        selectedProduct.value = product
    }

    fun setCategory(category: Category) {
        selectedCategory.value = category
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
