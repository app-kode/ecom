package com.bhuvancom.ecom.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity

@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "name", nullable = false, length = 100)
        var name: String = "",

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumns(JoinColumn(name = "role_id", referencedColumnName = "id"))
        var role: UserRole? = null,

        @Column(name = "password", length = 16)
        var userPassword: String = "",

        @Column(name = "email", nullable = false, unique = true, length = 100)
        var email: String = "",

        @Column(name = "phone", length = 20, nullable = false)
        var phone: String = "",

        @Column(name = "address")
        var address: String = "",

        @Column(name = "is_active", nullable = false)
        var isActive: Boolean = true,

        @Temporal(TemporalType.DATE)
        @Column(name = "last_access_date")
        var lastAccessDate: Date = Date()

) {
    constructor(id: Int?) : this() {
        this.id = id
        this.lastAccessDate = Date()
    }
}