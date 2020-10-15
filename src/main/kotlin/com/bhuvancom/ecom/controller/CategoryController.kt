package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Category
import com.bhuvancom.ecom.service.CategoryService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/category")
class CategoryController(private val categoryService: CategoryService) {

    @PostMapping("/save")
    fun saveCategory(@RequestBody category: Category): ResponseEntity<Category> {
        return categoryService.saveCategory(category)
    }

    @GetMapping("/all")
    fun getAllCategory(@RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        return categoryService.getAllCategories(pageNumber = page)
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