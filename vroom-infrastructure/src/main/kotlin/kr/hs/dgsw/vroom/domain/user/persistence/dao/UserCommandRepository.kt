package kr.hs.dgsw.vroom.domain.user.persistence.dao

import kr.hs.dgsw.vroom.domain.user.mapper.UserMapper
import kr.hs.dgsw.vroom.domain.user.model.User
import kr.hs.dgsw.vroom.domain.user.spi.query.UserCommandSpi
import org.springframework.stereotype.Component

@Component
class UserCommandRepository(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
): UserCommandSpi {
    override fun save(user: User?): User? {
        return userMapper.entityToDomain(userRepository.save(userMapper.domainToEntity(user!!)))
    }
}