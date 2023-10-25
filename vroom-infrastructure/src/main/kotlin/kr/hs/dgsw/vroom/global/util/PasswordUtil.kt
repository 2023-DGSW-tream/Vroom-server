package kr.hs.dgsw.vroom.global.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
object PasswordUtil {
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

    fun matches(actual: String, expected: String): Boolean {
        return passwordEncoder.matches(expected, actual)
    }

    fun encode(password: String): String {
        return passwordEncoder.encode(password)
    }
}