package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Cart
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CartRepository : JpaRepository<Cart,Int> {
    fun findTopCartByUserId(userId: Int): Optional<Cart>
}