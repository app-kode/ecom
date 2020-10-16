package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "order_products")
data class OrderProducts(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "quantity")
        var quantity: Int = 1,

        @ManyToOne(fetch = FetchType.LAZY,optional = false)
        @JoinColumns(JoinColumn(name = "product_id", referencedColumnName = "id",nullable = false))
        var product: Product? = null
)