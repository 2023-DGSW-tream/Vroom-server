package kr.hs.dgsw.vroom.web.auth

import kr.hs.dgsw.vroom.domain.auth.dto.request.UserRequest
import kr.hs.dgsw.vroom.domain.auth.dto.response.TokenResponse
import kr.hs.dgsw.vroom.domain.auth.usecase.LoginUseCase
import kr.hs.dgsw.vroom.domain.auth.usecase.RegisterUserUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUseCase
) {
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(
        @RequestBody request: UserRequest
    ) {
        registerUserUseCase.register(request)
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(
        @RequestBody request: UserRequest
    ): TokenResponse {
        return loginUseCase.login(request)
    }
}