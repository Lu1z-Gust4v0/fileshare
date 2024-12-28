package com.fileshare.fileshare.adapters.inbound.web.controllers

import com.fileshare.fileshare.adapters.inbound.web.controllers.requests.FileUploadRequest
import com.fileshare.fileshare.adapters.inbound.web.controllers.responses.FileUploadResponse
import com.fileshare.fileshare.application.services.FileService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/files")
@Validated
class FileController(
    private val fileService: FileService
) {
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(@ModelAttribute request: FileUploadRequest): ResponseEntity<FileUploadResponse> {
        fileService.uploadFile(request)

        return ResponseEntity.ok(FileUploadResponse(message = "File uploaded successfully", 200))
    }
}