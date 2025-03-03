package com.fileshare.fileshare.adapters.outbound.repositories

import com.fileshare.fileshare.adapters.outbound.persistance.UserPersistance
import com.fileshare.fileshare.adapters.outbound.persistance.repositories.UserRepository
import com.fileshare.fileshare.domain.user.User
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
class UserPersistanceTest {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userPersistance: UserPersistance

    @AfterAll
    fun clearDatabase() {
        userRepository.deleteAll()
    }

    private val testUser = User(name = "Said", email = "said@hotmail.com", password = "tobias")

    @Test
    fun `Should save a user to the database`() {
        // Arrange
        val user = testUser
        // Act
        val result = userPersistance.create(user).toDomain()
        // Assert
        assertThat(result).isEqualTo(user.copy(id = result.id))
    }

    @Test
    fun `Should check if there is already an email being user`() {
        // Arrange
        userPersistance.create(testUser)
        // Act
        val result = userPersistance.emailAlreadyExists(testUser.email)
        // Asset
        assertThat(result).isTrue()
    }
}