package com.fileshare.fileshare.adapters.outbound.persistance.repositories

import com.fileshare.fileshare.adapters.outbound.persistance.entities.FileEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FileRepository : JpaRepository<FileEntity, UUID> {
}