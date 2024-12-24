package com.fileshare.fileshare.adapters.outbound.persistance.entities

import jakarta.persistence.*
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "files")
open class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_id", nullable = false)
    open var id: UUID? = null

    @Column(name = "file_name", nullable = false)
    open var fileName: String? = null

    @Column(name = "file_key", nullable = false)
    open var fileKey: String? = null

    @Column(name = "uploaded_at", nullable = false)
    open var uploadedAt: ZonedDateTime = ZonedDateTime.now()

    @ManyToMany(mappedBy = "files")
    open var users: MutableCollection<UserEntity> = mutableListOf()
}