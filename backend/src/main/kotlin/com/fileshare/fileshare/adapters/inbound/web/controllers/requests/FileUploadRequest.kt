package com.fileshare.fileshare.adapters.inbound.web.controllers.requests

import com.fileshare.fileshare.domain.file.File
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.multipart.MultipartFile

data class FileUploadRequest(
    val file: MultipartFile,
) {
    fun toDomain(fileName: String): File {
        return File(
            fileName = file.originalFilename ?: fileName,
            fileKey = RandomStringUtils.randomAlphanumeric(File.FILE_KEY_LENGTH),
        )
    }
}