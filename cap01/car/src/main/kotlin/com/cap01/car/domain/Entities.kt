package com.cap01.car.domain

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Driver(
    @Id
    @GeneratedValue
    var id: Long? = null,
    val name: String,
    val birthDate: LocalDate
)

@Entity
data class Passenger(
    @Id
    @GeneratedValue
    var id: Long? = null,
    val name: String
)

enum class TravelRequestStatus {
    CREATED, ACCEPTED, REFUSED
}

@Entity
data class TravelRequest(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    val passenger: Passenger,
    val origin: String,
    val destination: String,
    val status: TravelRequestStatus = TravelRequestStatus.CREATED,
    val creationDate: LocalDateTime = LocalDateTime.now()
)
