package com.cap01.car.interfaces.incoming.mapping

import com.cap01.car.domain.PassengerRepository
import com.cap01.car.domain.TravelRequest
import com.cap01.car.interfaces.TravelRequestAPI
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class TravelRequestMapper (
    val passengerRepository: PassengerRepository
) {
    fun map(input: TravelRequestAPI.TravelRequestInput) : TravelRequest {
        val passenger = passengerRepository.findById(input.passengerId).
                orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND)}
        return TravelRequest(
            passenger = passenger,
            origin = input.origin,
            destination = input.destination
            )
    }
}