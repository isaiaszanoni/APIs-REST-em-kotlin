package com.cap01.car.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TravelRequestRepository : JpaRepository<TravelRequest, Long> {}