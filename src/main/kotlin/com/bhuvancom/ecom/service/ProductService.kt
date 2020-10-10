package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Product
import com.bhuvancom.ecom.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ProductService {

    private val logger = Logger.getLogger(ProductService::class.toString())

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var additionalImageRepository: AdditionalImageRepository

    @Autowired
    private lateinit var tagsRepository: TagsService

    @Autowired
    private lateinit var productFullDetailRepository: ProductFullDetailRepository

    @Autowired
    private lateinit var productDescriptionRepository: ProductDescriptionRepository

    fun getProductsOfCategory(categoryId: Int): ResponseEntity<MutableList<Product>> =
            ResponseEntity.ok(productRepository.findAllByCategoryId(categoryId))

    fun getProductByOwnerId(ownerId: Int): ResponseEntity<MutableList<Product>> =
            ResponseEntity.ok(productRepository.findAllByOwnerId(ownerId))

    fun getActiveProducts(): ResponseEntity<MutableList<Product>> =
            ResponseEntity.ok(productRepository.findAllByIsActive(true))

    fun getAllProduct(): ResponseEntity<MutableList<Product>> =
            ResponseEntity.ok(productRepository.findAll())

    fun getProductsOfGivenTag(tag: String): ResponseEntity<MutableList<Product>> {
        val tagByTagString = tagsRepository.getTagByTagString(tag)
        val products = mutableListOf<Product>()
        tagByTagString.forEach {
            products.addAll(productRepository.findAllByTags(it.id!!))
        }
        return ResponseEntity.ok(products)
    }

    fun getProductsByInfo(info: String = ""): ResponseEntity<MutableList<Product>> {
        val result = mutableListOf<Product>()
        if (info.contains("under") || info.contains("below")) {
            val integer = info.split(" ")
            var int = -1
            try {
                integer.forEach {
                    int = it.toInt()
                    logger.info("searching product $info for $int")
                }
            } catch (numberFormatException: NumberFormatException) {
                logger.info("error converting number ${numberFormatException.message}")
            }

            if (int != -1) {
                result.addAll(productRepository.findAllByPriceLessThanEqualOrderByPrice(int))
            }
        }
        val strArray = info.split(" ")
        result.addAll(productRepository.findAllByInfoContaining(strArray.toTypedArray()))
        return ResponseEntity.ok(result)
    }

    fun saveProduct(product: Product): ResponseEntity<Product> {
        val save = productRepository.save(product)
        return ResponseEntity.accepted().body(save)
    }

    fun getProductById(id: Int): ResponseEntity<Product>? {
        return productRepository.findById(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }
}
