package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "user_role")
data class UserRole(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "role", nullable = false, unique = true, updatable = false)
        var roleName: String = ""
)