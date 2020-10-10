package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "categories")
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int,

        @Column(name = "category_name")
        var categoryName: String = "",

        @Column(name = "discount")
        var discount: Double = 0.0
)