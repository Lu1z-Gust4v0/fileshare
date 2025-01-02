package com.fileshare.fileshare.adapters.outbound.repositories

import com.fileshare.fileshare.adapters.outbound.persistance.FileJPAPersistence
import com.fileshare.fileshare.adapters.outbound.persistance.repositories.FileRepository
import com.fileshare.fileshare.domain.file.File
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
class FilePersistanceTest {

    @Autowired
    private lateinit var fileRepository: FileRepository

    @Autowired
    private lateinit var filePersistance: FileJPAPersistence

    @AfterAll
    fun clearDatabase() {
        fileRepository.deleteAll()
    }

    @Test
    fun `Should save a file to the database`() {
        // Arrange
        val file = File(fileName = "test-file.txt", fileKey = "test-file-key")
        // Act
        val result = filePersistance.create(file)
        // Assert
        assertThat(result.fileKey).isEqualTo(file.fileKey)
        assertThat(result.fileName).isEqualTo(file.fileName)
    }
}