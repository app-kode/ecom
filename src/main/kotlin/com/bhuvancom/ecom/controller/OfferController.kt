package com.bhuvancom.ecom.controller

import com.bhuvancom.ecom.model.Offers
import com.bhuvancom.ecom.service.OfferService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/offers")
class OfferController(private val offerService: OfferService) {

    @GetMapping("/active")
    fun getAllActiveOffers(@RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        return offerService.getAllActiveOffers(pageNumber = page)
    }

    @GetMapping("/{id}")
    fun getOffer(@PathVariable id: Int): ResponseEntity<Offers> {
        return offerService.getOfferById(id)
    }

    @GetMapping("/category/{categoryId}")
    fun getOfferOfCategory(@PathVariable categoryId: Int, @RequestParam(name = "page", defaultValue = "1") page: Int = 1): ResponseEntity<HashMap<String, Any>> {
        return offerService.getOfferByCategoryId(categoryId, pageNumber = page)
    }

    @GetMapping("/all")
    fun getAllOffers(@RequestParam(name = "page", defaultValue = "1") page: Int = 1)
            : ResponseEntity<HashMap<String, Any>> {
        return offerService.getAllOffers(pageNumber = page)
    }


    @PostMapping("/save")
    fun saveOffer(@RequestBody offers: Offers): ResponseEntity<Offers> {
        return offerService.addOffer(offers)
    }

}