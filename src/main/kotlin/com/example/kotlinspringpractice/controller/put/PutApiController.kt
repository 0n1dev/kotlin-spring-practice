package com.example.kotlinspringpractice.controller.put

import com.example.kotlinspringpractice.model.http.UserRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest,
                         bindingResult: BindingResult): ResponseEntity<String> {

        if (bindingResult.hasErrors()) {

            val msg = StringBuilder()
            bindingResult.allErrors.forEach {
                val field = it as FieldError
                val message = it.defaultMessage

                msg.append("${field.field} $message\n")
            }

            return ResponseEntity.badRequest().body(msg.toString())
        }

//        // 0. Response
//        return UserResponse().apply {
//
//            // 1. result
//            Result().apply {
//                this.resultCode = "OK"
//                this.resultMessage = "성공"
//            }
//
//
//        }.apply {
//
//            // 2. description
//            this.description = "@@@@@@"
//        }.apply {
//
//            // 3. user mutable list
//            val userList = mutableListOf<UserRequest>()
//
//            userList.add(userRequest)
//            userList.add(UserRequest().apply {
//                this.name = "a"
//                this.age = 11
//                this.email = "test@naver.com"
//                this.address = "dd"
//                this.phoneNumber = "01018838303"
//            })
//
//            this.userRequest = userList
//        }
        return ResponseEntity.ok("")
    }
}