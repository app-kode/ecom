package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.Tags
import com.bhuvancom.ecom.repository.TagsRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TagsService(private val tagsRepository: TagsRepository) {

    fun addTag(tags: Tags) = ResponseEntity.ok(tagsRepository.save(tags))
    fun getAllTags(): MutableList<Tags> = tagsRepository.findAll()
    fun getTagByTagString(tag: String): MutableList<Tags> = tagsRepository.findAllByTag(tag)

}