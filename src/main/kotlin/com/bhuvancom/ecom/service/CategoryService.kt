package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Category
import com.bhuvancom.ecom.repository.CategoryRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CategoryService {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository
    private val logger = LoggerFactory.getLogger(CategoryService::class.java.name)

    fun getAllCategories(pageSize: Int = 10, pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val map = HashMap<String,Any>()
        val data = categoryRepository.findAll(page)
        map["data"] = data.content
        map["totalItem"]  = data.totalElements
        map["pageNumber"] = data.number + 1
        map["itemPerPage"] = data.size
        map["isFirstPage"] = data.isFirst
        map["isLastPage"] = data.isLast
        return ResponseEntity.ok(map)
    }

    fun saveCategory(categories: Category): ResponseEntity<Category> {
        logger.info("adding category $categories")
        return ResponseEntity.accepted().body(categoryRepository.save(categories))
    }

    fun getById(id: Int): ResponseEntity<Category>? {
        return categoryRepository.findById(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun getByName(name: String): ResponseEntity<Category>? {
        return categoryRepository.findTopByCategoryName(name).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }
}