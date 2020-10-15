package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Cart
import com.bhuvancom.ecom.service.CartService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
@CrossOrigin
class CartController(private val cartService: CartService) {

    @GetMapping("/{userId}")
    fun getCart(@PathVariable userId: Int): ResponseEntity<Cart> {
        return cartService.getCartOfUserId(userId)
    }

    @PostMapping("/save")
    fun saveCart(@RequestBody cart: Cart): ResponseEntity<Cart> {
        return cartService.saveCart(cart)
    }


    @DeleteMapping("/delete/{cartId}/{productId}")
    fun deleteProductFromCart(@PathVariable cartId: Int, @PathVariable productId: Int): ResponseEntity<Unit> {
        return cartService.removeProductFromCart(cartId, productId)
    }

    @DeleteMapping("/delete/products/{cartId}")
    fun deleteAllProducts(@PathVariable cartId: Int): ResponseEntity<Unit> {
        return cartService.removeProductsFromCart(cartId)
    }

    @GetMapping("/all")
    fun getAllCart(@RequestParam(name = "page",defaultValue = "1") page:Int): ResponseEntity<HashMap<String, Any>> {
        return cartService.findAllCart(page)
    }

}