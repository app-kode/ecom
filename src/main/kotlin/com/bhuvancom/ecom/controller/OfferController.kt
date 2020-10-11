package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Offers
import com.bhuvancom.ecom.service.OfferService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/offers")
class OfferController(private val offerService: OfferService) {

    @GetMapping("/active")
    fun getAllActiveOffers(): ResponseEntity<MutableList<Offers>> {
        return offerService.getAllActiveOffers()
    }

    @GetMapping("/{id}")
    fun getOffer(@PathVariable id: Int): ResponseEntity<Offers> {
        return offerService.getOfferById(id)
    }

    @GetMapping("/category/{categoryId}")
    fun getOfferOfCategory(@PathVariable categoryId: Int) {
        return offerService.getOfferByCategoryId(categoryId)
    }

    @GetMapping("/all")
    fun getAllOffers(): ResponseEntity<MutableList<Offers>> {
        return offerService.getAllOffers()
    }


    @PostMapping("/save")
    fun saveOffer(offers: Offers): ResponseEntity<Offers> {
        return offerService.addOffer(offers)
    }

}