package com.fileshare.fileshare.adapters.inbound.web.controllers

import com.fileshare.fileshare.adapters.inbound.web.controllers.requests.FileUploadRequest
import com.fileshare.fileshare.application.services.FileService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.multipart.MultipartFile

@WebMvcTest(FileController::class)
class FileControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var fileService: FileService

    private val MEGABYTE: Long = 1024 * 1024

    @Test
    fun `Should upload a file`() {
        // Arrange
        val validMockFile = mockk<MultipartFile> {
            every { originalFilename } returns "valid-file.pdf"
            every { contentType } returns MediaType.APPLICATION_PDF_VALUE
            every { size } returns 49 * MEGABYTE
            every { bytes } returns ByteArray(0)
        }

        val uploadRequest = FileUploadRequest(file = validMockFile)

        justRun { fileService.uploadFile(any()) }

        // Act
        val result = mockMvc.perform(
            MockMvcRequestBuilders.multipart("/v1/files")
                .file("file", uploadRequest.file.bytes)
        )

        // Assert
        result.andExpect(status().isOk)
    }
}