package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "product_description")
data class ProductDescription(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "description")
        var description: String = ""
)