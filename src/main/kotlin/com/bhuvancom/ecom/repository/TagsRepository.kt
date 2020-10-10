package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.Tags
import org.springframework.data.jpa.repository.JpaRepository

interface TagsRepository : JpaRepository<Tags, Int> {
    fun findAllByTag(tag: String): MutableList<Tags>
}