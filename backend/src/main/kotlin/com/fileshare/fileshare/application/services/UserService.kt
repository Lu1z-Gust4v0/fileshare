package com.fileshare.fileshare.application.services

import com.fileshare.fileshare.adapters.inbound.web.controllers.requests.CreateUserRequest
import com.fileshare.fileshare.adapters.outbound.persistance.UserPersistance
import com.fileshare.fileshare.domain.user.User
import com.fileshare.fileshare.exceptions.ServiceException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userPersistance: UserPersistance
) {
    fun createUser(request: CreateUserRequest): User {
        if (userPersistance.emailAlreadyExists(request.email)) {
            throw ServiceException("Email already in use", BAD_REQUEST)
        }

        return userPersistance.create(request.fromDomain()).toDomain()
    }
}