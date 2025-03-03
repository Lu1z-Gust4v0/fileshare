package com.fileshare.fileshare.adapters.inbound.web.controllers.responses

import com.fileshare.fileshare.domain.user.User

data class CreateUserResponse(
    val message: String,
    val code: Int,
    val user: User,
)
