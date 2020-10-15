package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "cart_products")
data class CartProducts(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @OneToOne(fetch = FetchType.LAZY,optional = false)
        @JoinColumns(JoinColumn(name = "product_id",referencedColumnName = "id",nullable = false))
        var product: Product? = null,

        @Column(name = "quantity")
        var quantity: Int = 1
)