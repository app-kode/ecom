package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface OrdersRepository : JpaRepository<Order, Int> {
    fun findAllByUserId(userId: Int, page: Pageable): Page<Order>
    fun findAllByStatus(status: String,page: Pageable): Page<Order>
    fun findAllByStatusAndUserId(status: String, userId: Int,page: Pageable): Page<Order>
}