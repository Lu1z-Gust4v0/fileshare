import com.fileshare.fileshare.adapters.outbound.persistance.entities.UserEntity
import com.fileshare.fileshare.adapters.outbound.persistance.repositories.UserRepository
import com.fileshare.fileshare.domain.user.User
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class UserPersistance(
    private val userRepository: UserRepository
) {
    fun findById(id: Long) = userRepository.findById(id).getOrNull()?.toDomain()

    fun create(user: User) = userRepository.save(UserEntity.fromDomain(user))

    fun emailAlreadyExists(email: String) = userRepository.existsByEmail(email)
}