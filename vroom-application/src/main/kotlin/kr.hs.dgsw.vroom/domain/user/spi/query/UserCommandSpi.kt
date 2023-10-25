package kr.hs.dgsw.vroom.domain.user.spi.query

import kr.hs.dgsw.vroom.domain.user.model.User

interface UserCommandSpi {
    fun save(user: User?): User?
}