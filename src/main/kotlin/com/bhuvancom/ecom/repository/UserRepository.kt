package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
    @Query("from User where isActive = 1")
    fun getAllActiveUsers(): MutableList<User>

}