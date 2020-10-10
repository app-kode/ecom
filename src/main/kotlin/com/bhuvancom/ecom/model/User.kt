package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "name")
        var name: String = "",

        @Column(name = "email")
        var email: String = "",

        @Column(name = "phone",length = 20)
        var phone: String = "",

        @Column(name = "address")
        var address: String = "",

        @Column(name = "is_active")
        var isActive: Boolean = false
)