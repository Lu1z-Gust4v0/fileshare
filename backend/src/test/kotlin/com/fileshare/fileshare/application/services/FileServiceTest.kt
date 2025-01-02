package com.fileshare.fileshare.application.services

import com.fileshare.fileshare.adapters.inbound.web.controllers.requests.FileUploadRequest
import com.fileshare.fileshare.adapters.outbound.persistance.FileJPAPersistence
import com.fileshare.fileshare.exceptions.ServiceException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.web.multipart.MultipartFile

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
class FileServiceTest {
    private val filePersistance: FileJPAPersistence = mockk(relaxed = true)

    private val fileService = FileService(
        filePersistance = filePersistance
    )

    private val MEGABYTE: Long = 1024 * 1024

    val validMockFile = mockk<MultipartFile> {
        every { originalFilename } returns "valid-file.pdf"
        every { contentType } returns MediaType.APPLICATION_PDF_VALUE
        every { size } returns 49 * MEGABYTE
        every { bytes } returns ByteArray(0)
    }

    val invalidFormatMockFile = mockk<MultipartFile> {
        every { originalFilename } returns "invalid-file.txt"
        every { contentType } returns MediaType.TEXT_PLAIN_VALUE
        every { size } returns 49 * MEGABYTE
        every { bytes } returns ByteArray(0)
    }

    val invalidSizeMockFile = mockk<MultipartFile> {
        every { originalFilename } returns "invalid-file.txt"
        every { contentType } returns MediaType.TEXT_PLAIN_VALUE
        every { size } returns 51 * MEGABYTE
        every { bytes } returns ByteArray(0)
    }

    @Test
    fun `Should accept and create a valid file`() {
        // Arrange / Act
        fileService.uploadFile(FileUploadRequest(validMockFile))
        // Assert
        verify {
            filePersistance.create(any())
        }
    }

    @Test
    fun `Should throw an exception if file format is invalid`() {
        // Arrange / Act
        assertThrows<ServiceException>("File must be PDF, PNG or JPEG!") {
            fileService.uploadFile(FileUploadRequest(invalidFormatMockFile))
        }
    }

    @Test
    fun `Should throw an exception if file size is greater than limit`() {
        // Arrange / Act
        assertThrows<ServiceException>("File is too big") {
            fileService.uploadFile(FileUploadRequest(invalidSizeMockFile))
        }
    }
}