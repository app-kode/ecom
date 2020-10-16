package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "product_tag")
data class ProductTag(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var productTag: Int? = null,

        @ManyToOne(fetch = FetchType.LAZY, optional = false,
                cascade = [CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH])
        @JoinColumns(JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false))
        var tag: Tags? = null
)