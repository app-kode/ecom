package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "tags")
data class Tags(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "tag_name", nullable = false)
        var tag: String = ""

//        @ManyToMany(fetch = FetchType.LAZY,mappedBy = "tags")
//        @JoinTable(name = "product_tags")
//        val tags: MutableList<Product> = mutableListOf()
)