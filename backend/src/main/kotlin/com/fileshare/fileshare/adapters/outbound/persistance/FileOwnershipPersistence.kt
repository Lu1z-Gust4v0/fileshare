package com.fileshare.fileshare.adapters.outbound.persistance

import com.fileshare.fileshare.adapters.outbound.persistance.entities.FileEntity
import com.fileshare.fileshare.adapters.outbound.persistance.entities.FileOwnershipEntity
import com.fileshare.fileshare.adapters.outbound.persistance.entities.UserEntity
import com.fileshare.fileshare.adapters.outbound.persistance.repositories.FileOwnershipRepository
import com.fileshare.fileshare.domain.user.Ownership
import org.springframework.stereotype.Repository

@Repository
class FileOwnershipPersistence(
    private val fileOwnershipRepository: FileOwnershipRepository
) {
    fun create(
        ownership: Ownership
    ): FileOwnershipEntity = fileOwnershipRepository.save(
        FileOwnershipEntity().apply {
            user = UserEntity.fromDomain(ownership.user)
            file = FileEntity.fromDomain(ownership.file)
        }
    )
}
