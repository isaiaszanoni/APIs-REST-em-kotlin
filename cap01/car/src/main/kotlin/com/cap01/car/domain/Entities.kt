package com.cap01.car.domain

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Driver(
    @Id
    @GeneratedValue
    var id: Long? = null,
    val name: String,
    val birthDate: LocalDate
)

data class PatchDriver(
    val name: String?,
    val birthDate: LocalDate?
)

@Entity
data class Passenger(
    @Id
    @GeneratedValue
    var id: Long? = null,
    val name: String
)

data class PatchPassenger(
    val name: String?
)