package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun getAllByIsActive(isActive: Boolean, pageable: Pageable): Page<User>
    fun findAllByNameContaining(name: String, page: Pageable): Page<User>
    fun findByEmail(email: String): User?
}