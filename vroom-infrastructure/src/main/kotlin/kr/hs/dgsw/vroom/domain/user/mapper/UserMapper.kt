package kr.hs.dgsw.vroom.domain.user.mapper

import kr.hs.dgsw.vroom.domain.user.model.User
import kr.hs.dgsw.vroom.domain.user.persistence.entity.UserEntity
import kr.hs.dgsw.vroom.global.mappers.Mapper
import org.springframework.stereotype.Component

@Component
class UserMapper: Mapper<User, UserEntity> {
    override fun domainToEntity(domain: User): UserEntity {
        return domain.run {
            UserEntity(
                id = id,
                email = domain.email,
                accountId = accountId,
                password = password,
                role = role
            )
        }
    }

    override fun entityToDomain(entity: UserEntity?): User? {
        return entity?.run {
            User(
                id = id,
                email = entity.email,
                accountId = accountId,
                password = password,
                role = role
            )
        }
    }
}