package com.example.sampleapplication

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.sampleapplication.view.MainActivity
import com.example.sampleapplication.view.adapter.CategoriesAdapter
import com.example.sampleapplication.view.adapter.ProductsAdapter
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers.greaterThan
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ApplicationTest {

    private val listItemInTest = 0
    private val categoryInTest = MockCategoryData.categoryList[listItemInTest]

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isCategoriesListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.categoriesList)).check(matches(isDisplayed()))
        onView(withId(R.id.loadingView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.categoriesError)).check(matches(not(isDisplayed())))
    }

    @Test
    fun test_CategoriesListNotEmpty() {
        activityRule.scenario.onActivity { activity ->
            val viewById = activity.findViewById<View>(R.id.categoriesList)
            assertThat(viewById, notNullValue())
            assertThat(viewById, instanceOf(RecyclerView::class.java))
            val listView = viewById as RecyclerView
            val adapter = listView.adapter as CategoriesAdapter
            assertThat(adapter, instanceOf(RecyclerView.Adapter::class.java))
            assertThat(adapter.itemCount, greaterThan(0))
        }
    }

    @Test
    fun test_selectCategoryListItem_isProductListFragmentVisible() {
        // Click list item #listItemInTest
        onView(withId(R.id.categoriesList)).perform(actionOnItemAtPosition<CategoriesAdapter.CategoryViewHolder>(listItemInTest, click()))
        // Confirm nav to ProductsFragment and check if product list is visible
        onView(withId(R.id.productsList)).check(matches(isDisplayed()))
    }

    @Test
    fun test_backNavigation_toCategoriesListFragment() {
        // Click list item #listItemInTest
        onView(withId(R.id.categoriesList)).perform(actionOnItemAtPosition<CategoriesAdapter.CategoryViewHolder>(listItemInTest, click()))
        // Confirm nav to ProductsFragment and display productsList
        onView(withId(R.id.productsList)).check(matches(isDisplayed()))
        pressBack()
        // Confirm Categories list in view
        onView(withId(R.id.categoriesList)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navDetailsFragment() {
        // Click list item #listItemInTest
        onView(withId(R.id.categoriesList)).perform(actionOnItemAtPosition<CategoriesAdapter.CategoryViewHolder>(listItemInTest, click()))
        // Confirm nav to ProductsFragment and display productsList
        onView(withId(R.id.productsList)).check(matches(isDisplayed()))
        // Click list item #listItemInTest
        onView(withId(R.id.productsList)).perform(actionOnItemAtPosition<ProductsAdapter.ProductViewHolder>(listItemInTest, click()))
        // Confirm correct product name is visible in DetailsFragment
        onView(withId(R.id.productName)).check(matches(withText(categoryInTest.products[listItemInTest].name)))
    }

    @Test
    fun test_backNavigation_toProductListFragment() {
        // Click list item #listItemInTest
        onView(withId(R.id.categoriesList)).perform(actionOnItemAtPosition<CategoriesAdapter.CategoryViewHolder>(listItemInTest, click()))
        // Confirm nav to ProductsFragment and display productsList
        onView(withId(R.id.productsList)).check(matches(isDisplayed()))
        // Click list item #listItemInTest
        onView(withId(R.id.productsList)).perform(actionOnItemAtPosition<ProductsAdapter.ProductViewHolder>(listItemInTest, click()))
        // Confirm correct product name is visible in DetailsFragment
        onView(withId(R.id.productName)).check(matches(withText(categoryInTest.products[listItemInTest].name)))
        pressBack()
        // Confirm products list in view
        onView(withId(R.id.productsList)).check(matches(isDisplayed()))
    }
}
