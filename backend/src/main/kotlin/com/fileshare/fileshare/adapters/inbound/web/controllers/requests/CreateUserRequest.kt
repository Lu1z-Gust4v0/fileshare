package com.fileshare.fileshare.adapters.inbound.web.controllers.requests

import com.fileshare.fileshare.domain.user.User

data class CreateUserRequest(
    val name: String,
    val email: String,
    val password: String,
) {
    fun toDomain() = User(
        name = name,
        email = email,
        password = password
    )
}