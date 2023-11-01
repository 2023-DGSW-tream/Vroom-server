package kr.hs.dgsw.vroom.domain.user.facade

import kr.hs.dgsw.vroom.domain.user.mapper.UserMapper
import kr.hs.dgsw.vroom.domain.user.model.User
import kr.hs.dgsw.vroom.domain.user.persistence.dao.UserQueryRepository
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userQueryRepository: UserQueryRepository,
    private val userMapper: UserMapper
) {
    fun getUserById(id: Long): User {
        return userQueryRepository.findById(id) ?: throw RuntimeException()
    }
}