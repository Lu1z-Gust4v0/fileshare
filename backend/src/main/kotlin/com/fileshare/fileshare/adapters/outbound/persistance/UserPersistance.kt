import com.fileshare.fileshare.adapters.outbound.persistance.repositories.UserRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class UserPersistance(
   private val userRepository: UserRepository
) {
   fun findById(id: Long) = userRepository.findById(id).getOrNull()?.toDomain()
}