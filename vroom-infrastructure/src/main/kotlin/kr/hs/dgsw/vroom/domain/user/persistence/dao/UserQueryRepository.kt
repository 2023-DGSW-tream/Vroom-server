package kr.hs.dgsw.vroom.domain.user.persistence.dao

import com.google.cloud.Identity.user
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.hs.dgsw.vroom.domain.user.mapper.UserMapper
import kr.hs.dgsw.vroom.domain.user.model.User
import kr.hs.dgsw.vroom.domain.user.persistence.entity.QUserEntity
import kr.hs.dgsw.vroom.domain.user.persistence.entity.UserEntity
import kr.hs.dgsw.vroom.domain.user.spi.query.UserQuerySpi
import org.springframework.stereotype.Repository


@Repository
class UserQueryRepository(
    private val userMapper: UserMapper,
    private val queryFactory: JPAQueryFactory
): UserQuerySpi {
    override fun findById(id: Long): User? {
        val userEntity: QUserEntity = QUserEntity.userEntity
        val entity: UserEntity? = queryFactory.selectFrom(userEntity)
            .where(userEntity.id.eq(id))
            .fetchOne()
        return userMapper.entityToDomain(entity)
    }
    override fun findByEmail(email: String): User? {
       val userEntity: QUserEntity = QUserEntity.userEntity
       val entity: UserEntity? = queryFactory.selectFrom(userEntity)
            .where(userEntity.email.eq(email))
            .fetchOne()
        return userMapper.entityToDomain(entity)
    }

    override fun existsByEmail(email: String): Boolean {
        val userEntity: QUserEntity = QUserEntity.userEntity
        val fetchFirst = queryFactory
            .selectOne()
            .from(userEntity)
            .where(userEntity.email.eq(email))
            .fetchFirst()
        return fetchFirst != null
    }


}