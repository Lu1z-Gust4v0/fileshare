package com.fileshare.fileshare.exceptions

import org.springframework.http.HttpStatus

class ServiceException(message: String, val status: HttpStatus) : RuntimeException(message)
