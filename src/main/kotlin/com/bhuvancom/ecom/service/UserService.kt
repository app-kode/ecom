package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.User
import com.bhuvancom.ecom.model.UserRole
import com.bhuvancom.ecom.repository.UserRepository
import com.bhuvancom.ecom.utility.PagedData
import com.bhuvancom.ecom.utility.Utility.Companion.PAGE_SIZE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.logging.Logger


@Service
@Transactional
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    fun passwordEncrypt(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }


    val logger = Logger.getLogger(this::class.java.name)

    fun getAllActiveUser(pageNumber: Int): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(userRepository.getAllByIsActive(true, page) as Page<Any>))
    }

    fun getAllUsers(pageNumber: Int): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(userRepository.findAll(page) as Page<Any>))
    }

    fun saveUser(user: User): ResponseEntity<User> {
        user.userPassword = passwordEncrypt().encode(user.userPassword)
        return ResponseEntity.accepted().body(userRepository.save(user))
    }

    fun findAllUserByName(name: String, pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(userRepository.findAllByNameContaining(name, page) as Page<Any>))
    }

    override fun loadUserByUsername(email: String): UserDetails {
        logger.info("checking for $email")
        //val encode = passwordEncoder.encode("1234")
        //logger.info("1234 hash is $encode")
        val user = userRepository.findByEmail(email)
        if (user == null) {
            logger.info("error user not found")
            throw UsernameNotFoundException("No User found")
        } else {
            logger.info("user $user")
            val userDetails = org.springframework.security.core.userdetails.User(
                    user.email,
                    user.userPassword,
                    mapRole(user.role!!)
            )
            logger.info("user details $userDetails")
            return userDetails
        }

    }

    private fun mapRole(role: UserRole): Collection<GrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority(role.roleName))
    }

}