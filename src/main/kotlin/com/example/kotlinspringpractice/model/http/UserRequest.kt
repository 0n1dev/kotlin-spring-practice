package com.example.kotlinspringpractice.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) PropertyNamingStrategy Class Deprecated
data class UserRequest(
    var name: String?=null,
    var age: Int?=null,
    var email: String?=null,
    var address: String?=null,

    // @JsonProperty("phone_number") Json에서는 스네이크케이스를 주로 사용해서 서로 매핑되도록 지정.
    var phoneNumber: String?=null // 코틀린에서는 기본적으로 카멜케이스 사용.
)