package kr.hs.dgsw.vroom.domain.user.persistence.dao

import kr.hs.dgsw.vroom.domain.user.persistence.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<UserEntity, Long> {
}