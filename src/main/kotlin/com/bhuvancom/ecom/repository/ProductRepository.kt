package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<Product, Int> {

    fun findAllByCategoryId(categoryId: Int, page: Pageable): Page<Product>
    fun findAllByOwnerId(userId: Int, page: Pageable): Page<Product>
    fun findAllByIsActive(isActive: Boolean, page: Pageable): Page<Product>
    fun findAllByTagsTagTag(tag: String, page: Pageable): Page<Product>

    //    fun findAllByInfoContaining(info: Array<String>, page: PageRequest): Page<Product>
    fun findAllByInfoContaining(info: String, page: Pageable): Page<Product>

    //@Query("from Product where info like '%:info%' or price <= :price order by price")
    fun findAllByInfoContainingAndPriceLessThanEqualOrderByPrice(info: String, price: Double, page: Pageable): Page<Product>

    @Query(countQuery = "select count(*) from Products",
            nativeQuery = true, value = "select * from Products order by rand()")
    fun findRandom(page: Pageable): Page<Product>
}