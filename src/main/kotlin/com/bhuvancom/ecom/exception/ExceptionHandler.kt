package com.bhuvancom.ecom.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders,
                                              status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errors = mutableListOf<String>()
        ex.bindingResult.fieldErrors.forEach { error -> errors.add(error.field + ":" + error.defaultMessage) }

        ex.bindingResult.globalErrors.forEach { error -> errors.add(error.objectName + ":" + error.defaultMessage) }

        val apiException = ApiException(HttpStatus.BAD_REQUEST, ex.localizedMessage, Arrays.asList(errors).toString())

        return handleExceptionInternal(ex, apiException, headers, apiException.httpStatus, request)
    }

    override fun handleHttpRequestMethodNotSupported(ex: HttpRequestMethodNotSupportedException, headers: HttpHeaders,
                                                     status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val msgBuilder = StringBuilder()
        msgBuilder.append(ex.method)
        msgBuilder.append(" method not supported for this request.")

        val apiException = ApiException(HttpStatus.METHOD_NOT_ALLOWED, ex.localizedMessage, msgBuilder.toString())
        return ResponseEntity(apiException, HttpHeaders(), apiException.httpStatus)
    }


    override fun handleNoHandlerFoundException(ex: NoHandlerFoundException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val msg = "No handler found for request " + ex.httpMethod + " " + ex.requestURL
        val apiException = ApiException(HttpStatus.NOT_FOUND, ex.localizedMessage, msg)
        return ResponseEntity(apiException, HttpHeaders(), apiException.httpStatus)
    }

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleAll(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        var msg = ""
        ex.cause?.message?.let {
            msg = it
        }
        val apiException = ApiException(HttpStatus.INTERNAL_SERVER_ERROR, msg, "Error Occurred")
        return ResponseEntity(apiException, HttpHeaders(), apiException.httpStatus)
    }
}