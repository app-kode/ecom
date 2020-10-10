package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Category
import com.bhuvancom.ecom.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CategoryService {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    fun getAllCategories(): ResponseEntity<MutableList<Category>> = ResponseEntity.ok(categoryRepository.findAll())
    fun saveCategory(categories: Category): ResponseEntity<Category> {
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