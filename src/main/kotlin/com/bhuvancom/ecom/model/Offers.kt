package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "offers")
data class Offers(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "is_active")
        var isActive: Boolean = false,

        @Column(name = "description")
        var descriptionTitle: String = "",

        @Column(name = "sub_title")
        var subTitle: String = "",

//        @ManyToMany(fetch = FetchType.LAZY)
//        @JoinTable(name = "offer_category")
//        val categories: MutableList<Category> = mutableListOf(),

        @OneToMany(fetch = FetchType.EAGER)
        @JoinColumn(name = "offer_id")
        val categories: MutableList<OffersCategory> = mutableListOf(),

        @Column(name = "img_url")
        var imgUrl: String = ""

)