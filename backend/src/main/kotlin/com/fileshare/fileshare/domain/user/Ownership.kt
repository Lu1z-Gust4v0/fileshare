import com.fileshare.fileshare.domain.file.File
import com.fileshare.fileshare.domain.user.User
import java.time.ZonedDateTime

data class Ownership(
    val user: User,
    val file: File,
    val createdAt: ZonedDateTime? = ZonedDateTime.now()
)