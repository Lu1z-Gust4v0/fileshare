import com.fileshare.fileshare.adapters.outbound.persistance.entities.FileEntity
import com.fileshare.fileshare.adapters.outbound.persistance.entities.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.EAGER
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "file_ownership")
open class FileOwnershipEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    open var id: Long? = null

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: UserEntity? = null

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "file_id", nullable = false)
    open var file: FileEntity? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: ZonedDateTime = ZonedDateTime.now()
}