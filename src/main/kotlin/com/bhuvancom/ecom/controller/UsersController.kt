package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.ResponseResource
import com.bhuvancom.ecom.model.User
import com.bhuvancom.ecom.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.logging.Logger

@RestController
@RequestMapping("/user")
class UsersController(private val userService: UserService) {
    private val logger = Logger.getLogger(UsersController::class.java.name)

    @GetMapping("/active")
    fun showAllActive(): ResponseEntity<MutableList<User>> {
        return ResponseEntity.ok(userService.getAllActiveUser())
    }

    @GetMapping("/all")
    fun showAll(): ResponseEntity<MutableList<User>> {
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @PostMapping("/save")
    fun addUser(@RequestBody user: User): ResponseEntity<User> {
        val saveUser = userService.saveUser(user)
        logger.info("user saved $saveUser")
        return ResponseEntity.ok(saveUser)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<ResponseResource<String>> {
        logger.info("deleting user with id $id")
        userService.deleteUser(id)
        return ResponseEntity(ResponseResource("Deleted", "success"), HttpStatus.OK)
    }
}