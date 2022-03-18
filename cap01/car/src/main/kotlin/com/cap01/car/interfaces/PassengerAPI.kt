package com.cap01.car.interfaces

import com.cap01.car.domain.Passenger
import com.cap01.car.domain.PassengerRepository
import com.cap01.car.domain.PatchPassenger
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@Service
@RestController
@RequestMapping(path = ["/passengers"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PassengerAPI (
    val passengerRepository: PassengerRepository
){
    @GetMapping("/list")
    fun listPassenger() = passengerRepository.findAll()

    @GetMapping("/{id}")
    fun findPassenger(@PathVariable("id") id: Long) =
        passengerRepository.findById(id).orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND) }

    @PostMapping
    fun createPassenger(@RequestBody passenger: Passenger) =
        passengerRepository.save(passenger)

    @PutMapping("/{id}")
    fun fullUpdatePassenger(@PathVariable("id") id: Long,
                            @RequestBody passenger: Passenger): Passenger {
        val newPassenger = findPassenger(id).copy(name = passenger.name)
        return passengerRepository.save(newPassenger)
    }

    @PatchMapping("/{id}")
    fun incrementalUpdatePassenger(@PathVariable("id") id: Long,
        @RequestBody passenger: PatchPassenger): Passenger {

        val foundPassenger = findPassenger(id)
        val newPassenger = foundPassenger.copy(
            name = passenger.name ?: foundPassenger.name
        )
        return passengerRepository.save(newPassenger)
    }

    @DeleteMapping("/{id}")
    fun deletePassenger(@PathVariable("id") id: Long) =
        passengerRepository.delete(findPassenger(id))
}