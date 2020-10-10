package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Cart
import com.bhuvancom.ecom.service.CartService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(private val cartService: CartService) {

    @GetMapping("/{userId}")
    fun getCart(@PathVariable userId: Int) {
        //todo:return cart
    }

    @PostMapping("/save/{cartId}")
    fun saveCart(cart: Cart, @PathVariable cartId: Int) {
        //todo:add product to cart and return cart
    }

    @DeleteMapping("/delete/{cartId}")
    fun deleteCart(@PathVariable cartId: Int) {
        //todo:delete cart
    }

    @DeleteMapping("/delete/{cartId}/{productId}")
    fun deleteProductFromCart(@PathVariable cartId: Int, @PathVariable productId: Int) {
        //todo:delete single product from cart and return cart
    }

    @DeleteMapping("/delete/products/{cartId}")
    fun deleteAllProducts(@PathVariable cartId: Int) {
        //todo:delete all products from given cart
    }

    @GetMapping("/all")
    fun getAllCart() {
        //todo:return all cart for admin only
    }

}