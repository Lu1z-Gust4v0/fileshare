package com.fileshare.fileshare.adapters.inbound.web.controllers

import com.fileshare.fileshare.adapters.inbound.web.controllers.requests.CreateUserRequest
import com.fileshare.fileshare.adapters.inbound.web.controllers.responses.CreateUserResponse
import com.fileshare.fileshare.application.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
@Validated
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<CreateUserResponse> {
        val user = userService.createUser(request)

        return ResponseEntity(
            CreateUserResponse(message = "User created successfully", code = 201, user = user.copy(password = null)),
            HttpStatus.CREATED
        )
    }
}