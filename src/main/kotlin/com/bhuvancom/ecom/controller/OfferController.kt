package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Offers
import com.bhuvancom.ecom.service.OfferService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/offers")
class OfferController(private val offerService: OfferService) {

    @GetMapping("/active")
    fun getAllActiveOffers() {
        //todo: return only active offers
    }

    @GetMapping("/{id}")
    fun getOffer(@PathVariable id: Int) {
        //todo:return offer if exist
    }

    @GetMapping("/category/{categoryId}")
    fun getOfferOfCategory(@PathVariable categoryId: Int) {
        //todo: return offer where category id is given
    }

    @GetMapping("/all")
    fun getAllOffers() {
        //todo:return all offers
    }

    @PostMapping("/save")
    fun saveOffer(offers: Offers) {
        //todo: save given offer
    }

}