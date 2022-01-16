package com.example.kotlinspringpractice.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ErrorResponse(
    var resultCode: String?=null,
    var httpStatus: HttpStatus?=null,
    var message: String?=null,
    var path: String?=null,
    var timestamp: LocalDateTime?=null,
    var errors: MutableList<Error>?= mutableListOf()
)

data class Error(
    var field: String?=null,
    var message: String?=null
)
