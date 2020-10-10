package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Order
import com.bhuvancom.ecom.repository.OrdersRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OrderService(private val ordersRepository: OrdersRepository) {

    fun getAllOrders(): ResponseEntity<MutableList<Order>> {
        return ResponseEntity.ok(ordersRepository.findAll())
    }

    fun getOrdersOfUser(userId: Int): ResponseEntity<MutableList<Order>> {
        return ResponseEntity.ok(ordersRepository.findAllByUserId(userId))
    }

    fun getAllOrderByStatus(status: String): ResponseEntity<MutableList<Order>> {
        return ResponseEntity.ok(ordersRepository.findAllByStatus(status))
    }

    fun getOrderById(id: Int): ResponseEntity<Order> {
        return ordersRepository.findById(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun getOrderByStatusOfUser(status: String, userId: Int): ResponseEntity<MutableList<Order>> {
        return ResponseEntity.ok(ordersRepository.findAllByStatusAndUserId(status, userId))
    }
}