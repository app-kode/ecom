package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "order_products")
data class OrderProducts(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int,

        @Column(name = "quantity")
        var quantity: Int = -1,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id")
        var product: Product? = null
)