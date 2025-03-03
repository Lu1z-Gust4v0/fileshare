package com.fileshare.fileshare.adapters.outbound.persistance.entities

import com.fileshare.fileshare.domain.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "users")
open class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    open var id: Long? = null

    @Column(name = "name", nullable = false)
    open var name: String? = null

    @Column(name = "email", nullable = false)
    open var email: String? = null

    @Column(name = "password", nullable = false)
    open var password: String? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: ZonedDateTime = ZonedDateTime.now()

    @OneToMany(mappedBy = "user", fetch = LAZY)
    open var fileOwnership: MutableSet<FileOwnershipEntity> = mutableSetOf()

    fun toDomain() = User(
        id = this.id,
        name = this.name.toString(),
        email = this.email.toString(),
        password = this.password.toString(),
        createdAt = this.createdAt,
    )

    companion object {
        fun fromDomain(user: User) = UserEntity().apply {
            id = user.id
            name = user.name
            email = user.email
            password = user.password
            createdAt = user.createdAt
        }
    }
}