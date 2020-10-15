package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Cart
import com.bhuvancom.ecom.repository.CartRepository
import com.bhuvancom.ecom.utility.PagedData
import com.bhuvancom.ecom.utility.Utility.Companion.PAGE_SIZE
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
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

    fun removeProductFromCart(cartId: Int, productId: Int): ResponseEntity<Unit> =
            cartRepository.findById(cartId).map { cart ->
                cart.cartProducts.remove(cart.cartProducts.find { it.id == productId })
                cartRepository.save(cart)
                ResponseEntity<Unit>(HttpStatus.ACCEPTED)
            }.orElse(ResponseEntity.notFound().build())

    fun saveCart(cart: Cart): ResponseEntity<Cart> {
        return ResponseEntity.accepted().body(cartRepository.save(cart))
    }

    fun findAllCart(pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(cartRepository.findAll(page) as Page<Any>))
    }


}