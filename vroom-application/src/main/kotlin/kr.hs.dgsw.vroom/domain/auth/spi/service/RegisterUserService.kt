package kr.hs.dgsw.vroom.domain.auth.spi.service

import kr.hs.dgsw.vroom.domain.auth.dto.request.UserRequest

interface RegisterUserService {
    fun register(request: UserRequest)
}