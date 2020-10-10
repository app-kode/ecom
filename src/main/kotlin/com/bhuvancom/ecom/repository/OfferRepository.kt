package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Offers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : JpaRepository<Offers, Int> {
}