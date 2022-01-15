package com.example.kotlinspringpractice.controller.response

import com.example.kotlinspringpractice.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get 4xx
    @GetMapping
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {
//        // 1. age == null -> 400 error
//        if (age == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락되었습니다.")
//        }
//
//        // 2. age < 20 -> 400 error
//        if (age < 20) {
//            return ResponseEntity.badRequest().body("age 값은 20보다 커야 합니다.")
//        }

        // 위 코드를 코틀린스럽게 리팩토링
        age?.let {
            // age not null
            if (age < 20) {
                return ResponseEntity.badRequest().body("age 값은 20보다 커야 합니다.")
            }

            return ResponseEntity.ok("OK")
        }?: kotlin.run {
            // age is null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락되었습니다.")
        }
    }

    // 2. post 200
    @PostMapping
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.ok().body(userRequest)
    }

    // 3. put 201
    @PutMapping
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 1. 기존 데이터가 없어서 새로 생성
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. delete 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Nothing> {
        return ResponseEntity.status(500).body(null)
    }
}