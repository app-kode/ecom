package com.bhuvancom.ecom.utility

import org.apache.tomcat.jni.User.username
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


class Utility {

    companion object {
        val STATUS = listOf("In Progress", "Completed", "Failed")
        const val DRIVER_CLASS = "jdbc:postgres://ec2-34-233-43-35.compute-1.amazonaws.com:5432/d7pmpgrqbd5fdb?sslMode=require&user=gzuzziomiimucn&password=bb9aa4fe2f943652cfa93f35f1b8a4c7d6614227154ab837d5506930ad2c74ee"
        const val USERNAME = "gzuzziomiimucn"
        const val PASSWORD = "bb9aa4fe2f943652cfa93f35f1b8a4c7d6614227154ab837d5506930ad2c74ee"
    }
}