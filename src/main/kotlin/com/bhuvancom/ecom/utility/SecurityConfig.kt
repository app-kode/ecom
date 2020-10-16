package com.bhuvancom.ecom.utility

import com.bhuvancom.ecom.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@Component
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    private val logger = LoggerFactory.getLogger(this::class.java.name)

    @Autowired
    lateinit var userService: UserService

    override fun configure(http: HttpSecurity) {

        logger.info("security overriding")

        http.csrf().disable()
        http.formLogin().disable()
        http.logout().disable()
        http.httpBasic { it ->
            it.realmName("e-com")
            it.authenticationDetailsSource { dd ->
                logger.info("${dd.authType} and ${dd.queryString} ${dd.method}")
                dd.headerNames.asIterator().forEach {
                    logger.info("$it -> ${dd.getHeader(it)}")
                }
                logger.info("remote user " + dd.remoteUser)
            }
        }
        http.authorizeRequests().antMatchers(
                "/",
                "/category/all",
                "/product/all",
                "/offers/all",
                "/logout",
                "/offers/category/***").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/user/save").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun passwordEncrypt(): BCryptPasswordEncoder {
        logger.info("bcrypt overriding")
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticatorProvider(): DaoAuthenticationProvider {
        logger.info("auth overriding")
        val auth = DaoAuthenticationProvider()
        auth.setUserDetailsService(userService)
        auth.setPasswordEncoder(passwordEncrypt())
        return auth
    }


    @Bean
    fun cors(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                logger.info("cors overriding")
                registry.addMapping("/***").allowedMethods("*")
                        .allowedOrigins("*").allowedHeaders("*")
            }
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(authenticatorProvider())
    }

}