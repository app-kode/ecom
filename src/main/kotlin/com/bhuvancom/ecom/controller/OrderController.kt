package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Order
import com.bhuvancom.ecom.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController(private val orderService: OrderService) {

    @PostMapping("/save")
    fun saveOrder(order: Order) {
        //todo:dave|update order and return order
    }

    @GetMapping("/user/{userId}")
    fun allOrders(@PathVariable userId: Int) {
        //todo:return all orders of given user
    }

    @GetMapping("/all")
    fun allOrders() {
        //todo:return all order list exist in system for admin
    }

    @GetMapping("/{id}")
    fun order(@PathVariable id: Int) {
        //todo:return order if exist
    }

}