package com.fileshare.fileshare.adapters.inbound.web.controllers.exceptions

data class ErrorResponse(
    val message: String = "An unexpected error happened.",
    val errors: List<ErrorDetailResponse> = emptyList()
)

data class ErrorDetailResponse(
    val error: String,
    val message: String? = null,
    val detail: String? = null
)
