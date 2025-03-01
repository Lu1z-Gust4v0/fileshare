package com.fileshare.fileshare.adapters.outbound.persistance.entities

import FileOwnershipEntity
import com.fileshare.fileshare.domain.file.File
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "files")
open class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id", nullable = false)
    open var id: Long? = null

    @Column(name = "file_name", nullable = false)
    open var fileName: String? = null

    @Column(name = "file_key", nullable = false)
    open var fileKey: String? = null

    @Column(name = "uploaded_at", nullable = false)
    open var uploadedAt: ZonedDateTime = ZonedDateTime.now()

    @OneToMany(mappedBy = "file", fetch = LAZY)
    open var fileOwnership: MutableSet<FileOwnershipEntity> = mutableSetOf()

    fun fromDomain(file: File) = FileEntity().apply {
        id = file.id
        fileName = file.fileName
        fileKey = file.fileKey
        uploadedAt = file.uploadedAt
    }

    fun toDomain() = File(
        id = this.id,
        fileName = this.fileName.toString(),
        fileKey = this.fileKey.toString(),
        uploadedAt = this.uploadedAt
    )
}