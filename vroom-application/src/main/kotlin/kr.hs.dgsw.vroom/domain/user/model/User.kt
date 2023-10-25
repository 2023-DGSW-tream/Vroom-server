package kr.hs.dgsw.vroom.domain.user.model

import kr.hs.dgsw.vroom.domain.user.model.value.Role

data class User(
    val id: Long = 0,
    val email: String,
    val accountId: String,
    val password: String,
    val role: Role
)