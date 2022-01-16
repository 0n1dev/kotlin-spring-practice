package com.example.kotlinspringpractice.controller.exception

import com.example.kotlinspringpractice.model.http.Error
import com.example.kotlinspringpractice.model.http.ErrorResponse
import com.example.kotlinspringpractice.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {
    
    @GetMapping("/hello")
    fun hello() {
        if (true) {
            throw RuntimeException("강제 Exception 발생")
        }
    }

    @GetMapping("/index-out-of-bound")
    fun indexOutOfBound(): String {
        val list = mutableListOf<String>()
        // val temp = list[0]

        return "hello"
    }

    @GetMapping
    fun get(
        @NotBlank
        @Size(min = 2, max = 10)
        @RequestParam name: String,

        @Min(10)
        @RequestParam age: Int
    ): String {
        return "$name $age"
    }


    @PostMapping
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        return userRequest
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {

        // 1. 에러 분석
        val errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach {
            val error = Error().apply {
                val field = it as FieldError
                this.field = field.field
                this.message = it.defaultMessage
            }

            errors.add(error)
        }

        // 2. ErrorReponse
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "Fail"
            this.httpStatus = HttpStatus.BAD_REQUEST
            this.message = "요청에 에러가 발생."
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        // 3. ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(e: ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        // 1. 에러 분석
        val errors = mutableListOf<Error>()

        e.constraintViolations.forEach {
            val field = it.propertyPath.last().name
            val message = it.message
            val error = Error().apply {
                this.field = field
                this.message = message
            }

            errors.add(error)
        }

        // 2. ErrorReponse
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "Fail"
            this.httpStatus = HttpStatus.BAD_REQUEST
            this.message = "요청에 에러가 발생."
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        // 3. ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class]) // 해당 Controller 내부에서 예외 핸들링
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
    }
}