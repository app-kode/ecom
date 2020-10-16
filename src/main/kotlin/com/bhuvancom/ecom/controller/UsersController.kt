package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.User
import com.bhuvancom.ecom.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.logging.Logger

@RestController
@CrossOrigin
@RequestMapping("/user")
class UsersController(private val userService: UserService) {
    private val logger = Logger.getLogger(UsersController::class.java.name)

    @GetMapping("/active")
    fun showAllActive(@RequestParam(name = "page", defaultValue = "1") page: Int = 1)
            : ResponseEntity<HashMap<String, Any>> {
        return userService.getAllActiveUser(page)
    }

    @GetMapping("/all")
    fun showAll(@RequestParam(name = "page", defaultValue = "1") page: Int = 1)
            : ResponseEntity<HashMap<String, Any>> {
        return userService.getAllUsers(page)
    }

    @PostMapping("/save")
    fun addUser(@RequestBody user: User): ResponseEntity<User> {
        logger.info("incoming user $user")
        val saveUser = userService.saveUser(user)
        logger.info("user saved $saveUser")
        return userService.saveUser(user)
    }

    @GetMapping("/search")
    fun findUserByName(@RequestParam("name") name: String = "",
                       @RequestParam(name = "page", defaultValue = "1") page: Int = 1)
            : ResponseEntity<HashMap<String, Any>> {
        return userService.findAllUserByName(name, page)
    }
}