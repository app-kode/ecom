package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "cart_products")
data class CartProducts(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id")
        var product: Product? = null,

        @Column(name = "quantity")
        var quantity: Int = 0
)