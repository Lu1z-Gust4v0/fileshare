package com.fileshare.fileshare.adapters.outbound.persistance.entities

import jakarta.persistence.*
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "users")
open class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    open var id: UUID? = null

    @Column(name = "email", nullable = false)
    open var email: String? = null

    @Column(name = "password", nullable = false)
    open var password: String? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: ZonedDateTime = ZonedDateTime.now()

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "file_ownership",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "file_id")],
    )
    open var files: MutableCollection<FileEntity> = mutableListOf()
}