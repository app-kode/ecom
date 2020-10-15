package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Order
import com.bhuvancom.ecom.repository.OrdersRepository
import com.bhuvancom.ecom.utility.PagedData
import com.bhuvancom.ecom.utility.Utility.Companion.PAGE_SIZE
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OrderService(private val ordersRepository: OrdersRepository) {

    fun getAllOrders(): ResponseEntity<MutableList<Order>> {
        return ResponseEntity.ok(ordersRepository.findAll())
    }

    fun getOrdersOfUser(userId: Int, pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(ordersRepository.findAllByUserId(userId, page) as Page<Any>))
    }

    fun getAllOrderByStatus(status: String, pageNumber: Int): ResponseEntity<Page<Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(ordersRepository.findAllByStatus(status, page) as Page<Any>)
    }

    fun getOrderById(id: Int): ResponseEntity<Order> {
        return ordersRepository.findById(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun getOrderByStatusOfUser(status: String, userId: Int, pageNumber: Int): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData
                .getPagedData(ordersRepository.findAllByStatusAndUserId(status, userId, page) as Page<Any>))
    }

    fun saveOrder(order: Order): ResponseEntity<Order> {
        return ResponseEntity.accepted().body(ordersRepository.save(order))
    }
}