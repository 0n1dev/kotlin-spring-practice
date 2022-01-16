package com.example.kotlinspringpractice.controller.exception

import com.example.kotlinspringpractice.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
internal class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun indexOutOfBoundTest() {
        mockMvc.perform(
            get("/api/exception/index-out-of-bound")
        ).andExpect(
            status().isOk
        ).andDo(print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()

        queryParams.add("name", "0n1dev")
        queryParams.add("age", "20")

        mockMvc.perform(
            get("/api/exception")
                .queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("0n1dev 20")
        ).andDo(print())
    }

    @Test
    fun getFailTest() {
        val queryParams = LinkedMultiValueMap<String, String>()

        queryParams.add("name", "0n1dev")
        queryParams.add("age", "1")

        mockMvc.perform(
            get("/api/exception")
                .queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.result_code").value("Fail")
        ).andDo(print())
    }

    @Test
    fun postTest() {
        val userRequest = UserRequest().apply {
            this.name = "0n1dev"
            this.age = 20
            this.phoneNumber = "010-3333-3333"
            this.address = "fawefawf"
            this.email = "test@naver.com"
            this.createdAt = "2022-01-16 18:50:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().`is`(200)
        ).andDo(print())
    }
}