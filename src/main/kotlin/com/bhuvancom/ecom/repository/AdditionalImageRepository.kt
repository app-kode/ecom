package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.AdditionalImages
import org.springframework.data.jpa.repository.JpaRepository

interface AdditionalImageRepository : JpaRepository<AdditionalImages, Int> {
}