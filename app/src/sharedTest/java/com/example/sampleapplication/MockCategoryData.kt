package com.example.sampleapplication

import com.example.sampleapplication.model.Category
import com.example.sampleapplication.model.Product
import com.example.sampleapplication.model.SalePrice

object MockCategoryData {
    private val salePrice1 = SalePrice("0.81", "EUR")
    private val salePrice2 = SalePrice("2.01", "EUR")
    private val product1 = Product("1", "36802", "Bread", "/Bread.jpg", "", salePrice1)
    private val product2 = Product("2", "36802", "Sandwich", "/Sandwich.jpg", "", salePrice2)
    private val products1 = arrayListOf(product1, product2)
    private val category1 = Category("36802", "Food", "", products1)
    val categoryList = arrayListOf(category1)
}
