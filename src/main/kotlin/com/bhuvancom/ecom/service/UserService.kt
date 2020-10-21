package com.bhuvancom.ecom.service

import com.bhuvancom.ecom.model.User
import com.bhuvancom.ecom.model.UserRole
import com.bhuvancom.ecom.repository.RoleRepository
import com.bhuvancom.ecom.repository.UserRepository
import com.bhuvancom.ecom.utility.PagedData
import com.bhuvancom.ecom.utility.Utility
import com.bhuvancom.ecom.utility.Utility.Companion.PAGE_SIZE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.util.*
import java.util.logging.Logger
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Service
@Transactional
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    fun passwordEncrypt(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @PersistenceContext
    private lateinit var session: EntityManager


    val logger = Logger.getLogger(this::class.java.name)

    fun getAllActiveUser(pageNumber: Int): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData
                .getPagedData(userRepository.getAllByIsActive(true, page) as Page<Any>))
    }

    fun getAllUsers(pageNumber: Int): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(userRepository.findAll(page) as Page<Any>))
    }

    fun saveUser(user: User): ResponseEntity<User> {
        return if (user.id != null) {
            val builder = session.criteriaBuilder
            val criteriaUpdate = builder.createCriteriaUpdate(User::class.java)
            val root = criteriaUpdate.from(User::class.java)

            if (Utility.isNotNullOrEmpty(user.address))
                criteriaUpdate.set(root.get("address"), user.address)

            if (Utility.isNotNullOrEmpty(user.email))
                criteriaUpdate.set(root.get("email"), user.email)

            if (Utility.isNotNullOrEmpty(user.name))
                criteriaUpdate.set(root.get("name"), user.name)

            if (Utility.isNotNullOrEmpty(user.phone))
                criteriaUpdate.set(root.get("phone"), user.phone)

            if (Utility.isNotNullOrEmpty(user.userPassword)) {
                user.userPassword = passwordEncrypt().encode(user.userPassword)
                criteriaUpdate.set(root.get("userPassword"), user.userPassword)
            }

            criteriaUpdate.set(root.get("isActive"), user.isActive)

            val equal = builder.equal(root.get<Int>("id"), user.id!!)
            criteriaUpdate.where(equal)
            session.createQuery(criteriaUpdate).executeUpdate()
            ResponseEntity.accepted().body(userRepository.getOne(user.id!!))
        } else {
            if (userRepository.findByEmail(user.email) != null)
                throw Exception("Email ID in Use", Throwable("Email not found"))
            else {
                user.userPassword = passwordEncrypt().encode(user.userPassword)
                ResponseEntity.accepted().body(userRepository.save(user))
            }
        }
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun findAllUserByName(name: String, pageNumber: Int = 1): ResponseEntity<HashMap<String, Any>> {
        val page = PageRequest.of(pageNumber - 1, PAGE_SIZE)
        return ResponseEntity.ok(PagedData.getPagedData(userRepository.findAllByNameContaining(name, page) as Page<Any>))
    }

    override fun loadUserByUsername(email: String): UserDetails {
        logger.info("checking for $email")

        val user = userRepository.findByEmail(email)
        if (user == null) {
            logger.info("error user not found")
            throw Exception("No User found", Throwable("User not found"))
        } else {

            val userDetails = org.springframework.security.core.userdetails.User(
                    user.email,
                    user.userPassword,
                    mapRole(user.role!!)
            )
            logger.info("user details $userDetails")

            saveUser(User(user.id)) //update last access try date

            return userDetails
        }

    }

    private fun mapRole(role: UserRole): Collection<GrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority(role.roleName))
    }

    fun getAllRoles(): ResponseEntity<MutableList<UserRole>> {
        return ResponseEntity.ok().body(roleRepository.findAll())
    }

}