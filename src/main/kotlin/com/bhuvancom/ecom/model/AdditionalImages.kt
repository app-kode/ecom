package com.bhuvancom.ecom.model

import javax.persistence.*

@Entity
@Table(name = "additional_images")
data class AdditionalImages(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Int? = null,

        @Column(name = "img_url",nullable = false)
        var imgUrl: String = ""
)