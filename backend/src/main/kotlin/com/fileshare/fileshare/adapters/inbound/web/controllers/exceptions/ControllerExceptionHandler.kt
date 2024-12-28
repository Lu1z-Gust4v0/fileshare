package com.fileshare.fileshare.adapters.inbound.web.controllers.exceptions

import com.fileshare.fileshare.exceptions.ServiceException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ControllerExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(ServiceException::class)
    fun handleServiceException(exception: ServiceException): ResponseEntity<Any> {
        return ResponseEntity(ErrorResponse(exception.message.toString()), exception.status)
    }
}