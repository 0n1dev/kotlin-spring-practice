package com.example.kotlinspringpractice.controller.post

import com.example.kotlinspringpractice.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping(): String {
        return "post-mapping"
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "post-mapping"
    }

    // Spring에서 ObjectMapper를 사용
    // json -> object
    // object -> json

    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
        return userRequest
    }
}