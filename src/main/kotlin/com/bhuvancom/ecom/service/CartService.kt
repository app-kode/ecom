package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Cart
import com.bhuvancom.ecom.repository.CartRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CartService(private val cartRepository: CartRepository) {


    fun getCartOfUserId(userId: Int): ResponseEntity<Cart> {
        return cartRepository.findTopCartByUserId(userId).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun removeProductsFromCart(id: Int): ResponseEntity<Unit> = cartRepository.findById(id).map { cart ->
        cart.cartProducts.clear()
        cartRepository.save(cart)
        ResponseEntity<Unit>(HttpStatus.ACCEPTED)
    }.orElse(ResponseEntity.notFound().build())

    fun removeProductFromCart(userId: Int, productId: Int): ResponseEntity<Unit> =
            cartRepository.findTopCartByUserId(userId).map { cart ->
                cart.cartProducts.remove(cart.cartProducts.find { it.id == productId })
                cartRepository.save(cart)
                ResponseEntity<Unit>(HttpStatus.ACCEPTED)

            }.orElse(ResponseEntity.notFound().build())


}