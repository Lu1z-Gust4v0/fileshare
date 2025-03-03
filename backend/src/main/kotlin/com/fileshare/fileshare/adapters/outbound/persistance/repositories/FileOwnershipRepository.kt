package com.fileshare.fileshare.adapters.outbound.persistance.repositories

import com.fileshare.fileshare.adapters.outbound.persistance.entities.FileOwnershipEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FileOwnershipRepository : JpaRepository<FileOwnershipEntity, Long>
