package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.ProductDescription
import org.springframework.data.jpa.repository.JpaRepository

interface ProductDescriptionRepository:JpaRepository<ProductDescription,Int> {
}