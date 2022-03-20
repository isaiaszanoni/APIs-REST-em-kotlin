package com.cap01.car.interfaces


import com.cap01.car.domain.TravelService
import com.cap01.car.interfaces.incoming.mapping.TravelRequestMapper
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
@RestController
@RequestMapping(path = ["/travelRequests"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TravelRequestAPI (
    val travelService: TravelService,
    val mapper: TravelRequestMapper
) {

    data class TravelRequestInput(
        val passengerId: Long,
        val origin: String,
        val destination: String
    )



    @PostMapping
    fun makeTravelRequest(@RequestBody travelRequestInput: TravelRequestInput) {
        travelService.saveTravelRequest(mapper.map(travelRequestInput))
    }

}
