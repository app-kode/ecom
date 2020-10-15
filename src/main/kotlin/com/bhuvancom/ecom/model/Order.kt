package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @OneToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumns(JoinColumn(name = "user_id", referencedColumnName = "id"))
        val user: User? = null,

//        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
//        @JoinColumn(name = "product_id", referencedColumnName = "id")
//        val products: List<Product> = listOf(),

        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumns(JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false))
        val products: MutableList<OrderProducts> = mutableListOf(),

        @Column(name = "order_status")
        var status: String = "",

        @Column(name = "payment_status")
        val paymentStatus: String = ""
)