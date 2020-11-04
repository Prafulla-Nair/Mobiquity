package com.example.sampleapplication.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("products")
    val products: List<Product>
)

data class Product(
    @SerializedName("id")
    val id: String?,
    @SerializedName("categoryId")
    val categoryId: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("salePrice")
    val salePrice: SalePrice
)

data class SalePrice(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("currency")
    val currency: String?
)
