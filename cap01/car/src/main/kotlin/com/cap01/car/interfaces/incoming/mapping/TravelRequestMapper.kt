package com.cap01.car.interfaces.incoming.mapping

import com.cap01.car.domain.PassengerRepository
import com.cap01.car.domain.TravelRequest
import com.cap01.car.interfaces.PassengerAPI
import com.cap01.car.interfaces.TravelRequestAPI
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import com.cap01.car.interfaces.TravelRequestOutput
import com.cap01.car.interfaces.TravelRequestInput
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder


@Component
class TravelRequestMapper (
    val passengerRepository: PassengerRepository
) {

    fun map(input: TravelRequestInput) : TravelRequest {
        val passenger = passengerRepository.findById(input.passengerId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        return TravelRequest(passenger = passenger,
            origin = input.origin,
            destination = input.destination)
    }

    fun map(travelRequest: TravelRequest) : TravelRequestOutput {
        return TravelRequestOutput(
            id = travelRequest.id!!,
            origin = travelRequest.origin,
            destination = travelRequest.destination,
            status = travelRequest.status,
            creationDate = travelRequest.creationDate
        )
    }

    fun buildOutputModel(travelRequest: TravelRequest, output: TravelRequestOutput)
    : EntityModel<TravelRequestOutput>
    {
        val passengerLink = WebMvcLinkBuilder
            .linkTo(PassengerAPI::class.java)
            .slash(travelRequest.passenger.id)
            .withRel("passenger")
            .withTitle(travelRequest.passenger.name)
        return EntityModel.of(output, passengerLink)
    }
}