package com.bhuvancom.ecom.model

data class ResponseResource<T>(val data: T? = null, val message: String)