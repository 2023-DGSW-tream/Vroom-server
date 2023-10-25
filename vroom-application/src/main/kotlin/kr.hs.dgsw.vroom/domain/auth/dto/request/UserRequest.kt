package kr.hs.dgsw.vroom.domain.auth.dto.request

data class UserRequest(
    val email: String,
    val password: String
)