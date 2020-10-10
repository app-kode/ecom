package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Product
import com.bhuvancom.ecom.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController(private val productService: ProductService) {

    @PostMapping("/save")
    fun addProduct(product: Product): ResponseEntity<Product> {
        return productService.saveProduct(product)
    }

    @GetMapping("/{productId}")
    fun product(@PathVariable productId: Int): ResponseEntity<Product>? {
        return productService.getProductById(productId)
    }

    @GetMapping("/category/{categoryId}")
    fun productByCategory(@PathVariable categoryId: Int): ResponseEntity<MutableList<Product>> {
        return productService.getProductsOfCategory(categoryId)
    }

    @GetMapping("/user/{userid}")
    fun productOfOwner(@PathVariable userId: Int): ResponseEntity<MutableList<Product>> {
        return productService.getProductByOwnerId(userId)
    }

    @GetMapping("/all")
    fun products(): ResponseEntity<MutableList<Product>> {
        return productService.getActiveProducts()
    }

    @GetMapping("/allProduct")
    fun allProduct(): ResponseEntity<MutableList<Product>> {
        return productService.getAllProduct()
    }

    @GetMapping("/tags/{tag}")
    fun allProductByTag(@PathVariable tag: String): ResponseEntity<MutableList<Product>> {
        return productService.getProductsOfGivenTag(tag)
    }

    @GetMapping("/search/{search}")
    fun getProductBySearch(@PathVariable search: String): ResponseEntity<MutableList<Product>> {
        return productService.getProductsByInfo(search)
    }

}