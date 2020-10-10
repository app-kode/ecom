package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.ProductFullDetails
import org.springframework.data.jpa.repository.JpaRepository

interface ProductFullDetailRepository : JpaRepository<ProductFullDetails, Int> {
}