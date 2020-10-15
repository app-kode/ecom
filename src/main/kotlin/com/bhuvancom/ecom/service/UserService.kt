package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.User
import com.bhuvancom.ecom.repository.UserRepository
import com.bhuvancom.ecom.utility.PagedData
import com.bhuvancom.ecom.utility.Utility.Companion.PAGE_SIZE
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(private val userRepository: UserRepository) {

    fun getAllActiveUser(pageNumber: Int): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(userRepository.getAllByIsActive(true, page) as Page<Any>))
    }

    fun getAllUsers(pageNumber: Int): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(userRepository.findAll(page) as Page<Any>))
    }

    fun saveUser(user: User): User = userRepository.save(user)

    fun findAllUserByName(name: String, pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(userRepository.findAllByNameContaining(name, page) as Page<Any>))
    }
}