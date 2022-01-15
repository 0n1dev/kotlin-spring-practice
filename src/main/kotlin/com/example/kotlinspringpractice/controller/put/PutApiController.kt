package com.example.kotlinspringpractice.controller.put

import com.example.kotlinspringpractice.model.http.Result
import com.example.kotlinspringpractice.model.http.UserRequest
import com.example.kotlinspringpractice.model.http.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "put-mapping"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        // 0. Response
        return UserResponse().apply {

            // 1. result
            Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }


        }.apply {

            // 2. description
            this.description = "@@@@@@"
        }.apply {

            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()

            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 11
                this.email = "test@naver.com"
                this.address = "dd"
                this.phoneNumber = "01018838303"
            })

            this.userRequest = userList
        }
    }
}