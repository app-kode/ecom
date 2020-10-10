package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrdersRepository : JpaRepository<Order, Int> {
    fun findAllByUserId(userId: Int): MutableList<Order>
    fun findAllByStatus(status: String): MutableList<Order>
    fun findAllByStatusAndUserId(status: String, userId: Int): MutableList<Order>
}