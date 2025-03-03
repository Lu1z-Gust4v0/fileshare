package com.fileshare.fileshare.application.services

import com.fileshare.fileshare.adapters.inbound.web.controllers.requests.CreateUserRequest
import com.fileshare.fileshare.adapters.outbound.persistance.UserPersistance
import com.fileshare.fileshare.exceptions.ServiceException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class UserServiceTest {
    private val userPersistance = mockk<UserPersistance>(relaxed = true)

    private val userService = UserService(userPersistance)

    private val validRequest = CreateUserRequest(
        name = "salido",
        password = "tobias",
        email = "salido@hotmail",
    )

    @Test
    fun `Should create a user`() {
        // Arrange / Act
        userService.createUser(validRequest)

        // Assert
        verify {
            userPersistance.create(any())
        }
    }

    @Test
    fun `Should throw an error if a duplicated email is being used`() {
        // Arrange
        every {
            userPersistance.emailAlreadyExists(any())
        } returns true

        // Act
        assertThrows<ServiceException> {
            userService.createUser(validRequest)
        }
    }
}