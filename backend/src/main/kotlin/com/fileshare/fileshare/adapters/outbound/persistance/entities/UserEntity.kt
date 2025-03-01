package com.fileshare.fileshare.adapters.outbound.persistance.entities

import FileOwnershipEntity
import com.fileshare.fileshare.domain.user.User
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "users")
open class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    open var id: Long? = null

    @Column(name = "email", nullable = false)
    open var email: String? = null

    @Column(name = "password", nullable = false)
    open var password: String? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: ZonedDateTime = ZonedDateTime.now()

    @OneToMany(mappedBy = "user", fetch = LAZY)
    open var fileOwnership: MutableSet<FileOwnershipEntity> = mutableSetOf()

    fun fromDomain(user: User) = UserEntity().apply {
        id = user.id
        email = user.email
        password = user.password
        createdAt = user.createdAt
    }

    fun toDomain() = User(
        id = this.id,
        email = this.email.toString(),
        password = this.password.toString(),
        createdAt = this.createdAt,
    )
}