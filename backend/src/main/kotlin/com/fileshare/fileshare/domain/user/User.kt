package com.fileshare.fileshare.domain.user

import java.time.ZonedDateTime

data class User(
    val id: Long? = null,
    val name: String,
    val email: String,
    val password: String? = null,
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
)