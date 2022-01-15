package com.example.kotlinspringpractice.controller.get

import com.example.kotlinspringpractice.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello") // 현재 사용 되는 방식
    fun hello(): String {
        return "hello kotlin"
    }

    @RequestMapping(method = [RequestMethod.GET],
        path = ["request-mapping"]) // 예전에 사용 하던 방식
    fun requestMapping(): String {
        return "mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}") // PathVariable 받는 방법
    fun pathVariable(@PathVariable name: String): String {
        return name
    }

    @GetMapping("/get-mapping/path-variable2/{name}") // PathVariable 받는 방법2 (URI 내의 파라메터와 변수 이름이 다를 때)
    fun pathVariable2(@PathVariable(value = "name") abc: String): String {
        return abc
    }

    @GetMapping("/get-mapping/query-param") // QueryParam 받는 법
    fun queryParam(
        @RequestParam name: String,
        @RequestParam age:Int
    ): String {
        return "$name $age"
    }

    // 객체로 받아야 하는 경우 name, age, address, email
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        return userRequest
    }
}