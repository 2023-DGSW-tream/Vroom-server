package kr.hs.dgsw.vroom.domain.auth.usecase

import kr.hs.dgsw.vroom.common.annotation.UseCase
import kr.hs.dgsw.vroom.domain.auth.dto.request.UserRequest
import kr.hs.dgsw.vroom.domain.auth.dto.response.TokenResponse
import kr.hs.dgsw.vroom.domain.auth.spi.service.LoginUserService

@UseCase
class LoginUseCase(
    private val loginUserService: LoginUserService
) {
    fun login(request: UserRequest): TokenResponse {
        return loginUserService.login(request)
    }
}