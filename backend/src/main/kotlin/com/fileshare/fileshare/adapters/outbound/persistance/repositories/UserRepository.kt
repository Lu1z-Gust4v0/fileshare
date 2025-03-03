package com.fileshare.fileshare.adapters.outbound.persistance.repositories

import com.fileshare.fileshare.adapters.outbound.persistance.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun existsByEmail(email: String): Boolean
}
