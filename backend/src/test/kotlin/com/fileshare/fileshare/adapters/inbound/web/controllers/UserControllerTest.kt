package com.fileshare.fileshare.adapters.inbound.web.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fileshare.fileshare.adapters.inbound.web.controllers.requests.CreateUserRequest
import com.fileshare.fileshare.adapters.outbound.persistance.UserPersistance
import com.fileshare.fileshare.application.services.UserService
import com.fileshare.fileshare.exceptions.ServiceException
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var userPersistance: UserPersistance

    @MockkBean
    private lateinit var userService: UserService

    private val mapper = ObjectMapper()

    @Test
    fun `Should create a user`() {
        // Arrange
        val createUserRequest = CreateUserRequest(name = "salido", email = "salido@hotmail", password = "tobias")

        every { userService.createUser(any()) } returns createUserRequest.toDomain()
        // Act / Assert
        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createUserRequest))
        ).andExpect {
            status().isCreated
        }
    }

    @Test
    fun `Should return a bad request if the email is already in user`() {
        // Arrange
        val createUserRequest = CreateUserRequest(name = "salido", email = "salido@hotmail", password = "tobias")
        every { userService.createUser(any()) } throws ServiceException("Email already in use", BAD_REQUEST)
        // Act / Assert
        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createUserRequest))
        ).andExpect {
            status().isBadRequest
        }
    }
}