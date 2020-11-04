package com.example.sampleapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleapplication.model.CategoriesService
import com.example.sampleapplication.model.Category
import com.example.sampleapplication.viewmodel.SharedViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler.ExecutorWorker
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SharedViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var categoriesService: CategoriesService

    @InjectMocks
    var listViewModel = SharedViewModel()

    private var testSingle: Single<List<Category>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getCategoriesSuccess() {
        testSingle = Single.just(MockCategoryData.categoryList)

        `when`(categoriesService.getCategories()).thenReturn(testSingle)

        listViewModel.fetchCategories()

        Assert.assertEquals(1, listViewModel.categories.value?.size)
        Assert.assertEquals(false, listViewModel.categoriesLoadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Test
    fun getCategoriesFail() {
        testSingle = Single.error(Throwable())

        `when`(categoriesService.getCategories()).thenReturn(testSingle)

        listViewModel.fetchCategories()

        Assert.assertEquals(true, listViewModel.categoriesLoadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate: Scheduler = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorWorker({ runnable: Runnable -> runnable.run() }, true)
            }
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }
}
