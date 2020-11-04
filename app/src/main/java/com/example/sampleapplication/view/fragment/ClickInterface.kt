package com.example.sampleapplication.view.fragment

import com.example.sampleapplication.model.Category
import com.example.sampleapplication.model.Product

/**
 * Interface for click on the CategoriesFragment
 */
interface CategoryClickInterface {
    fun onClickListener(category: Category)
}

/**
 * Interface for click on the ProductsFragment
 */
interface ProductClickInterface {
    fun onClickListener(product: Product)
}
