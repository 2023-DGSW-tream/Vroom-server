package kr.hs.dgsw.vroom.domain.auth.usecase

import kr.hs.dgsw.vroom.common.annotation.UseCase
import kr.hs.dgsw.vroom.domain.auth.dto.request.UserRequest
import kr.hs.dgsw.vroom.domain.auth.spi.service.RegisterUserService

@UseCase
class RegisterUserUseCase(
    private val registerUserService: RegisterUserService
) {
    fun register(request: UserRequest) {
        println("*")
        registerUserService.register(request)
    }
}