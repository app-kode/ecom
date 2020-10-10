package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, Int> {
    fun findTopByCategoryName(name: String): Optional<Category>
}