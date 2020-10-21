package com.bhuvancom.ecom.repository

import com.bhuvancom.ecom.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<UserRole, Int>