package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "info", nullable = false)
        var info: String = "",

        //@OrderBy
        @Column(name = "price")
        var price: Double = 0.0,

        @Column(name = "is_active")
        var isActive: Boolean = true,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumns(JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false))
        var category: Category? = null,

        @Column(name = "img_url")
        var imgUrl: String = "",

        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumns(JoinColumn(name = "product_id", referencedColumnName = "id"))
        val tags: MutableList<ProductTag> = mutableListOf(),

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumns(JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false))
        var owner: User? = null,

        @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], optional = true)
        @JoinColumns(JoinColumn(name = "product_full_description", referencedColumnName = "id", nullable = true))
        var productDescription: ProductFullDetails? = null


)