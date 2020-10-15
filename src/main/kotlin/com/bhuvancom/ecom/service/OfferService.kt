package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Offers
import com.bhuvancom.ecom.repository.OfferRepository
import com.bhuvancom.ecom.utility.PagedData
import com.bhuvancom.ecom.utility.Utility.Companion.PAGE_SIZE
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
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

    fun getAllActiveOffers(pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        val data = offerRepository.findAllByIsActive(page = page)
        return ResponseEntity.ok(PagedData.getPagedData(data as Page<Any>))
    }

    fun getAllOffers(pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        val data = offerRepository.findAll(page)
        val pagedData = PagedData.getPagedData(data = data as Page<Any>)
        return ResponseEntity.ok(pagedData)
    }

    fun getOfferByCategoryId(categoryId: Int,pageNumber: Int=1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        val data = offerRepository.findAllByCategoriesCategoryId(categoryId,page)
        val pagedData = PagedData.getPagedData(data = data as Page<Any>)
        return ResponseEntity.ok(pagedData)
    }
}