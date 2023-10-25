package kr.hs.dgsw.vroom.domain.auth.service

import kr.hs.dgsw.vroom.domain.auth.dto.request.UserRequest
import kr.hs.dgsw.vroom.domain.auth.spi.service.RegisterUserService
import kr.hs.dgsw.vroom.domain.user.mapper.UserMapper
import kr.hs.dgsw.vroom.domain.user.model.User
import kr.hs.dgsw.vroom.domain.user.model.value.Role
import kr.hs.dgsw.vroom.domain.user.persistence.entity.UserEntity
import kr.hs.dgsw.vroom.domain.user.spi.query.UserCommandSpi
import kr.hs.dgsw.vroom.domain.user.spi.query.UserQuerySpi
import kr.hs.dgsw.vroom.global.util.PasswordUtil
import org.springframework.stereotype.Service

@Service
class RegisterUserServiceImpl(
    private val passwordUtil: PasswordUtil,
    private val userCommandSpi: UserCommandSpi,
    private val userQuerySpi: UserQuerySpi
): RegisterUserService {
    override fun register(request: UserRequest) {
        if (userQuerySpi.existsByEmail(request.email)) {
            throw RuntimeException("exists email")
        }
        userCommandSpi.save(User(
            email = request.email,
            accountId = initializeAccountId(request.email),
            password = passwordUtil.encode(request.password),
            role = Role.ROLE_USER
        ))
    }

    private fun initializeAccountId(email: String): String {
        return email.split("@")[0]
    }
}