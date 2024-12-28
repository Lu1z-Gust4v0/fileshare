package com.fileshare.fileshare.adapters.outbound.persistance

import com.fileshare.fileshare.adapters.outbound.persistance.entities.FileEntity
import com.fileshare.fileshare.adapters.outbound.persistance.repositories.FileRepository
import com.fileshare.fileshare.domain.file.File
import org.springframework.stereotype.Repository

@Repository
class FileJPAPersistence(
    private val fileRepository: FileRepository
) {
    fun create(
        file: File
    ): FileEntity = fileRepository.save(
        FileEntity().apply {
            fileName = file.fileName
            fileKey = file.fileKey
            uploadedAt = file.uploadedAt
        }
    )
}