import com.fileshare.fileshare.adapters.outbound.persistance.FileOwnershipPersistence
import com.fileshare.fileshare.adapters.outbound.persistance.FilePersistence
import com.fileshare.fileshare.exceptions.ServiceException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class AttachFileToUserUseCase(
    private val filePersistence: FilePersistence,
    private val userPersistance: UserPersistance,
    private val fileOwnershipPersistence: FileOwnershipPersistence
) {
    fun perform(userId: Long, fileId: Long) {
        val user = userPersistance.findById(userId)
            ?: throw ServiceException("User does not exist", HttpStatus.BAD_REQUEST)

        val file =
            filePersistence.findById(fileId) ?: throw ServiceException("File does not exist", HttpStatus.BAD_REQUEST)

        fileOwnershipPersistence.create(Ownership(user, file))
    }
}