package com.fileshare.fileshare.domain.user

import java.time.ZonedDateTime

data class User(
    val id: Long?,
    val email: String,
    val password: String,
    val createdAt: ZonedDateTime,
)