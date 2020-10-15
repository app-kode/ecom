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

        @Column(name = "description", nullable = false)
        var descriptionTitle: String = "",

        @Column(name = "sub_title")
        var subTitle: String = "",

        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumns(JoinColumn(name = "offer_id", referencedColumnName = "id", nullable = false))
        val categories: MutableList<OffersCategory> = mutableListOf(),

        @Column(name = "img_url")
        var imgUrl: String = ""

)