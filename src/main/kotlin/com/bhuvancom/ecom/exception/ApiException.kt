package com.bhuvancom.ecom.exception

import org.springframework.http.HttpStatus
import java.util.*

class ApiException : Exception {

    val httpStatus: HttpStatus
    val messages: String
    val errors: MutableList<String>

    constructor(httpStatus: HttpStatus,
                message: String,
                errors: MutableList<String> = mutableListOf()) {
        this.messages = message
        this.httpStatus = httpStatus
        this.errors = errors
    }

    constructor(httpStatus: HttpStatus, message: String = "", error: String) {
        this.errors = Arrays.asList(error)
        this.httpStatus = httpStatus
        this.messages = message
    }
}