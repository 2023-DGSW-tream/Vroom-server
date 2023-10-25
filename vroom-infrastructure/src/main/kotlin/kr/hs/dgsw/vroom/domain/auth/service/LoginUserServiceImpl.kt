package kr.hs.dgsw.vroom.domain.auth.service

import kr.hs.dgsw.vroom.domain.auth.dto.request.UserRequest
import kr.hs.dgsw.vroom.domain.auth.dto.response.TokenResponse
import kr.hs.dgsw.vroom.domain.auth.spi.service.LoginUserService
import kr.hs.dgsw.vroom.domain.user.persistence.dao.UserQueryRepository
import kr.hs.dgsw.vroom.global.security.jwt.JwtProvider
import kr.hs.dgsw.vroom.global.util.PasswordUtil
import org.springframework.stereotype.Service

@Service
class LoginUserServiceImpl(
    private val jwtProvider: JwtProvider,
    private val userQueryRepository: UserQueryRepository,
    private val passwordUtil: PasswordUtil
): LoginUserService {
    override fun login(request: UserRequest): TokenResponse {
        val user = userQueryRepository.findByEmail(request.email) ?: throw RuntimeException()
        if (!passwordUtil.matches(user.password, request.password)) {
            throw RuntimeException()
        }
        return TokenResponse(jwtProvider.generateToken(user.id))
    }
}