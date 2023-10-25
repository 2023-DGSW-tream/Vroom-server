package kr.hs.dgsw.vroom.global.security.auth

import kr.hs.dgsw.vroom.domain.user.model.User
import kr.hs.dgsw.vroom.domain.user.spi.query.UserQuerySpi
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailService(
    private val userQuerySpi: UserQuerySpi
): UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val user: User? = userQuerySpi.findById(id.toLong())

        if (user == null) {
            throw RuntimeException("유저를 찾지 못함")
        }

        return AuthUser(user)
    }
}