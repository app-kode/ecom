package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "product_full_details")
data class ProductFullDetails(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "full_description_id", referencedColumnName = "id")
        val descriptions: MutableList<ProductDescription> = mutableListOf(),

        @OneToMany( fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "full_description_id", referencedColumnName = "id")
        val images: MutableList<AdditionalImages> = mutableListOf(),

        @Column(name = "details")
        var details: String = ""


)