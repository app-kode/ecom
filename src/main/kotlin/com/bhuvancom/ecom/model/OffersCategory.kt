package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "offer_category")
data class OffersCategory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var offerCategoryId: Int,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id")
        var category: Category? = null
)