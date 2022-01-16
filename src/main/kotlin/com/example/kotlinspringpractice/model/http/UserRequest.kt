package com.example.kotlinspringpractice.model.http

import com.example.kotlinspringpractice.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) PropertyNamingStrategy Class Deprecated
data class UserRequest(
    // @Size(min = 2, max = 8) // 자바에서 사용하는 방식
    @field:NotEmpty
    @field:Size(min = 2, max = 8) // 코틀린에서 Data Class 필드에 어노테이션을 붙일 때
    var name: String?=null,

    @field:PositiveOrZero // 0 이상의 숫자를 검증
    var age: Int?=null,

    @field:Email // email 양식
    var email: String?=null,

    @field:NotBlank // 공백을 검증
    var address: String?=null,

    @field:Pattern(regexp = """^\d{2,3}-\d{3,4}-\d{4}$""") // 정규식 검증
    // @JsonProperty("phone_number") Json에서는 스네이크케이스를 주로 사용해서 서로 매핑되도록 지정.
    var phoneNumber: String?=null, // 코틀린에서는 기본적으로 카멜케이스 사용.

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var createdAt: String?=null
) {

//    @AssertTrue(message = "createdAt의 패턴이 잘못되었습니다.")
//    private fun isValidCreatedAt(): Boolean {
//
//        return try {
//            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            true
//        } catch (e: Exception) {
//            false
//        }
//    }
}