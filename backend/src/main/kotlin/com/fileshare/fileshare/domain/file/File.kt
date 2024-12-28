package com.fileshare.fileshare.domain.file

import java.time.ZonedDateTime

data class File(
    val id: String? = null,
    val fileName: String,
    val fileKey: String,
    val uploadedAt: ZonedDateTime = ZonedDateTime.now()
) {
    companion object {
        const val FILE_KEY_LENGTH = 20
    }
}