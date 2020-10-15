package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Product
import com.bhuvancom.ecom.repository.*
import com.bhuvancom.ecom.utility.PagedData
import com.bhuvancom.ecom.utility.Utility.Companion.PAGE_SIZE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.logging.Logger

@Service
@Transactional
class ProductService {

    private val logger = Logger.getLogger(ProductService::class.toString())

    @Autowired
    lateinit var productRepository: ProductRepository


    fun getProductsOfCategory(categoryId: Int, pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(productRepository.findAllByCategoryId(categoryId, page) as Page<Any>))
    }

    fun getProductByOwnerId(ownerId: Int, pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(productRepository.findAllByOwnerId(ownerId, page) as Page<Any>))
    }

    fun getActiveProducts(pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        logger.info("page created")
        return ResponseEntity.ok(PagedData.getPagedData(productRepository.findAllByIsActive(true, page) as Page<Any>))
    }

    fun getAllProduct(pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(productRepository.findAll(page) as Page<Any>))
    }

    fun getProductsOfGivenTag(tag: String, pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(productRepository.findAllByTagsTagTag(tag, page) as Page<Any>))
    }

    fun getProductsByInfo(info: String = "", pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        var price: Int = -1
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        if (info.contains("under") || info.contains("below")) {
            val integer = info.split(" ")
            logger.info("data slplit $integer")
            integer.forEach {
                try {
                    price = it.toInt()
                    logger.info("searching product $info for $price")
                } catch (numberFormatException: NumberFormatException) {
                    logger.info("error converting number ${numberFormatException.message}")
                }
            }
        }
        //val resultOne = (productRepository.findAllByInfoContaining(strArray.toTypedArray(), page) as Page<Any>)
        val resultOne = if (price == -1) {
            logger.info("searching with query only $info")
            productRepository
                    .findAllByInfoContaining(info, page)
        } else {
            logger.info("searching with $info or price $price")

            val inf = info.split(" ").filter {
                !(it.equals("under", true)
                        || it.equals("below", true)
                        || it == price.toString())
            }

            var query = ""
            var a = 0
            inf.forEach {
                query += (it)
                a++
                if (a != inf.size) query += " "
            }
            logger.info("final query $query")
            productRepository
                    .findAllByInfoContainingAndPriceLessThanEqualOrderByPrice(query, price.toDouble(), page)
            //.findByInfo(info, price, page)
        }

        return ResponseEntity.ok(PagedData.getPagedData(resultOne as Page<Any>))
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
