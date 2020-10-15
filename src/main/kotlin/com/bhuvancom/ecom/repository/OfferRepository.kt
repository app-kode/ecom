package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Offers
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : JpaRepository<Offers, Int> {

    fun findAllByIsActive(isActive: Boolean = true, page: Pageable): Page<Offers>
    fun findAllByCategoriesCategoryId(categoryId: Int, page: Pageable): Page<Offers>
}