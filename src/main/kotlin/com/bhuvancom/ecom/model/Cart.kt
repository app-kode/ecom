package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "carts")
data class Cart(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @OneToOne(optional = false, fetch = FetchType.EAGER)
        @JoinColumns(JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id"))
        var user: User? = null,

        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumns(JoinColumn(name = "cart_id", referencedColumnName = "id"))
        var cartProducts: MutableList<CartProducts> = mutableListOf()

//        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//        @JoinTable(name = "cart_products")
//        val products: MutableList<Product> = mutableListOf()
)