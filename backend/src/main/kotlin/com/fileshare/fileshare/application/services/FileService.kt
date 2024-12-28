package com.fileshare.fileshare.application.services

import com.fileshare.fileshare.adapters.inbound.web.controllers.requests.FileUploadRequest
import com.fileshare.fileshare.adapters.outbound.persistance.FileJPAPersistence
import com.fileshare.fileshare.exceptions.ServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.unit.DataSize
import org.springframework.web.multipart.MultipartFile
import java.lang.System.currentTimeMillis

@Service
class FileService(
    private val maxFileSize: String = "50MB",
    private val filePersistance: FileJPAPersistence
) {
    fun uploadFile(request: FileUploadRequest) {
        if (request.file.size > DataSize.parse(maxFileSize).toBytes()) {
            throw ServiceException(message = "File is too big", status = HttpStatus.BAD_REQUEST)
        }

        val fileName = "${currentTimeMillis()}.${getExtension(request.file)}"

        filePersistance.create(request.toDomain(fileName))
    }

    private fun getExtension(file: MultipartFile): String =
        when (file.contentType) {
            MediaType.APPLICATION_PDF_VALUE -> "pdf"
            MediaType.IMAGE_JPEG_VALUE -> "jpg"
            MediaType.IMAGE_PNG_VALUE -> "png"
            else -> throw ServiceException(message = "File must be PDF, PNG or JPEG!", status = HttpStatus.BAD_REQUEST)
        }
}