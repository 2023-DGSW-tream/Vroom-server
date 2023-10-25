package kr.hs.dgsw.vroom.domain.user.persistence.entity

import kr.hs.dgsw.vroom.domain.user.model.value.Role
import javax.persistence.*

@Table(name = "tbl_user")
@Entity
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val accountId: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: Role
) {
}