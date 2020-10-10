package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Int> {

    fun findAllByCategoryId(categoryId: Int): MutableList<Product>
    fun findAllByOwnerId(userId: Int): MutableList<Product>
    fun findAllByIsActive(isActive: Boolean): MutableList<Product>
    fun findAllByTags(tagId: Int): MutableList<Product>
    fun findAllByInfoContaining( info: Array<String>): MutableList<Product>
    fun findAllByPriceLessThanEqualOrderByPrice(int: Int): MutableList<Product>
}