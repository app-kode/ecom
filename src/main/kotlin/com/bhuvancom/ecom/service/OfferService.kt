package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Offers
import com.bhuvancom.ecom.repository.OfferRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OfferService(private val offerRepository: OfferRepository) {

    fun addOffer(offers: Offers): ResponseEntity<Offers> = ResponseEntity.ok(offerRepository.save(offers))

    fun getOfferById(id: Int): ResponseEntity<Offers> = offerRepository.findById(id).map { offer ->
        ResponseEntity.ok(offer)
    }.orElse(ResponseEntity.notFound().build())

    fun setOfferInActive(id: Int): ResponseEntity<Unit>? {
        return offerRepository.findById(id).map { offers ->
            offers.isActive = false
            offerRepository.save(offers)
            ResponseEntity<Unit>(HttpStatus.ACCEPTED)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun getAllActiveOffers(): ResponseEntity<MutableList<Offers>> {
        return ResponseEntity.ok(offerRepository.findAllActiveOffers())
    }

    fun getAllOffers(): ResponseEntity<MutableList<Offers>> {
        return ResponseEntity.ok(offerRepository.findAll())
    }

    fun getOfferByCategoryId(categoryId: Int) {
        offerRepository.findAllByCategoriesCategory(categoryId)
    }
}