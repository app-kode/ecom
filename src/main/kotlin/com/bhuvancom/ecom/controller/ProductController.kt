package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Product
import com.bhuvancom.ecom.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/product")
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java.name)

    @PostMapping("/save")
    fun addProduct(@RequestBody product: Product): ResponseEntity<Product> {
        logger.info("incoming product $product")
        return productService.saveProduct(product)
    }

    @GetMapping("/{productId}")
    fun product(@PathVariable productId: Int): ResponseEntity<Product>? {
        return productService.getProductById(productId)
    }

    @GetMapping("/category/{categoryId}")
    fun productByCategory(@PathVariable categoryId: Int, @RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        return productService.getProductsOfCategory(categoryId, page)
    }

    @GetMapping("/user/{userId}")
    fun productOfOwner(@PathVariable userId: Int, @RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        return productService.getProductByOwnerId(userId, page)
    }

    @GetMapping("/all")
    fun products(@RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        logger.info("incoming page $page")
        return productService.getActiveProducts(page)
    }

    @GetMapping("/allProduct")
    fun allProduct(@RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        return productService.getAllProduct(page)
    }

    @GetMapping("/tags/{tag}")
    fun allProductByTag(@PathVariable tag: String, @RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        return productService.getProductsOfGivenTag(tag, page)
    }

    @GetMapping("/search")
    fun getProductBySearch(@RequestParam(name = "search", defaultValue = "") search: String = "", @RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        return productService.getProductsByInfo(search, page)
    }

}