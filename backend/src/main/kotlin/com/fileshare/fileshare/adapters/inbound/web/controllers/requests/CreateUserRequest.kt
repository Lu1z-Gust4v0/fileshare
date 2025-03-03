import com.fileshare.fileshare.domain.user.User

data class CreateUserRequest(
    val name: String,
    val email: String,
    val password: String,
) {
    fun fromDomain() = User(
        name = name,
        email = email,
        password = password
    )
}