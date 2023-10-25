package kr.hs.dgsw.vroom.domain.user.spi.query

import kr.hs.dgsw.vroom.common.spi.QuerySpi
import kr.hs.dgsw.vroom.domain.user.model.User

interface UserQuerySpi: QuerySpi<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}