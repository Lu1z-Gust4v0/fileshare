package com.fileshare.fileshare.adapters.outbound.persistance.repositories

import FileOwnershipEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FileOwnershipRepository: JpaRepository<FileOwnershipEntity, Long> {}
