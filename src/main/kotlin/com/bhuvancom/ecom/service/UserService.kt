package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.User
import com.bhuvancom.ecom.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllActiveUser(): MutableList<User> = userRepository.getAllActiveUsers()

    fun getAllUsers(): MutableList<User> = userRepository.findAll()

    fun saveUser(user: User): User = userRepository.save(user)

    fun deleteUser(id: Int) = userRepository.deleteById(id)

}