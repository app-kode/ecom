package com.bhuvancom.ecom.exception

import org.springframework.http.HttpStatus
import java.util.*

class ApiException {

    val httpStatus: HttpStatus
    val message: String
    val errors: MutableList<String>

    constructor(httpStatus: HttpStatus,
                message: String,
                errors: MutableList<String> = mutableListOf()) {
        this.message = message
        this.httpStatus = httpStatus
        this.errors = errors
    }

    constructor(httpStatus: HttpStatus, message: String, error: String) {
        this.errors = Arrays.asList(error)
        this.httpStatus = httpStatus
        this.message = message
    }
}