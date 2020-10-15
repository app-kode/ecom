package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "offer_category")
data class OffersCategory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var offerCategoryId: Int? = null,

        @OneToOne(fetch = FetchType.LAZY,optional = false)
        @JoinColumns(JoinColumn(name = "category_id", referencedColumnName = "id",nullable = false))
        var category: Category? = null
)