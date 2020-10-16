package com.bhuvancom.ecom.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity

@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        open var id: Int? = null,

        @Column(name = "name", nullable = false)
        var name: String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumns(JoinColumn(name = "role_id", referencedColumnName = "id"))
        var role: UserRole? = null,

        @Column(name = "password")
        var userPassword: String = "",

        @Column(name = "email", nullable = false)
        var email: String = "",

        @Column(name = "phone", length = 20, nullable = false)
        var phone: String = "",

        @Column(name = "address")
        var address: String = "",

        @Column(name = "is_active", nullable = false)
        var isActive: Boolean = false

)