package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.ResponseResource
import com.bhuvancom.ecom.service.CartService
import com.bhuvancom.ecom.service.CategoryService
import com.bhuvancom.ecom.service.ProductService
import com.bhuvancom.ecom.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
@CrossOrigin
class MainController {

    companion object {
        private val LOGGER: Logger = Logger.getLogger(MainController::class.java.name)
    }

    @GetMapping("/")
    fun home(): ResponseEntity<ResponseResource<String>> {
        LOGGER.info("on home page")
        return ResponseEntity(ResponseResource("Working", "success"), HttpStatus.OK)
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<ResponseResource<String>> {
        return ResponseEntity(ResponseResource("Working", "success"), HttpStatus.OK)
    }


//    @GetMapping("/logout")
//    fun logout() {
//
//    }

    //@GetMapping("/tag")
}