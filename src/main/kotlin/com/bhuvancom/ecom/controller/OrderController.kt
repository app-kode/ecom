package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Order
import com.bhuvancom.ecom.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/order")
class OrderController(private val orderService: OrderService) {

    @PostMapping("/save")
    fun saveOrder(@RequestBody order: Order): ResponseEntity<Order> {
        return orderService.saveOrder(order)
    }

    @GetMapping("/user/{userId}")
    fun allOrders(@PathVariable userId: Int, @RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        return orderService.getOrdersOfUser(userId,page)
    }

    @GetMapping("/all")
    fun allOrders(): ResponseEntity<MutableList<Order>> {
        return orderService.getAllOrders()
    }

    @GetMapping("/{id}")
    fun order(@PathVariable id: Int): ResponseEntity<Order> {
        return orderService.getOrderById(id)
    }


}