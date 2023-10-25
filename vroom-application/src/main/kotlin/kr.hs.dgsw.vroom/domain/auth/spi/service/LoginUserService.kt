package kr.hs.dgsw.vroom.domain.auth.spi.service

import kr.hs.dgsw.vroom.domain.auth.dto.request.UserRequest
import kr.hs.dgsw.vroom.domain.auth.dto.response.TokenResponse

interface LoginUserService {
    fun login(request: UserRequest): TokenResponse
}