package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Offers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : JpaRepository<Offers, Int> {
    @Query("from Offers where isActive = 1")
    fun findAllActiveOffers():MutableList<Offers>
    fun findAllByCategoriesCategory(categoryId: Int):MutableList<Offers>
}