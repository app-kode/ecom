package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "info")
        var info: String = "",

        //@OrderBy
        @Column(name = "price")
        var price: Double = 0.0,

        @Column(name = "is_active")
        var isActive: Boolean = true,

        @OneToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "category_id", referencedColumnName = "id")
        var category: Category? = null,

        @Column(name = "img_url")
        var imgUrl: String = "",

        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinTable(name = "product_tags")
        val tags: MutableList<Tags> = mutableListOf(),

        @OneToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "owner_id")
        var owner: User? = null,

        @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "product_full_description", referencedColumnName = "id")
        var productDescription: ProductFullDetails? = null


)