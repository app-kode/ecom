package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Category
import com.bhuvancom.ecom.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
class CategoryController(private val categoryService: CategoryService) {

    @PostMapping("/save")
    fun saveCategory(category: Category) {
        categoryService.saveCategory(category)
    }

    @GetMapping("/all")
    fun getAllCategory(): ResponseEntity<MutableList<Category>> {
        return categoryService.getAllCategories()
    }

    @GetMapping("/{id}")
    fun getCategory(@PathVariable id: Int): ResponseEntity<Category>? {
        return categoryService.getById(id)
    }

    @GetMapping("/search/{category}")
    fun getCategoryByName(@PathVariable category: String): ResponseEntity<Category>? {
        return categoryService.getByName(category)
    }

}