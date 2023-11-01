package kr.hs.dgsw.vroom.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import kr.hs.dgsw.vroom.domain.user.facade.UserFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct


@Component
class JwtProvider(
    private val userFacade: UserFacade
) {
    @Value("\${vroom.jwt.accessKey}")
    private val secretKey: String? = null
    private var key: Key? = null

    @PostConstruct
    fun init() {
        this.key = Keys.hmacShaKeyFor(secretKey?.toByteArray());
    }

    fun generateToken(id: Long): String {
        val now: Instant = Instant.now()
        return Jwts.builder()
            .setSubject(id.toString())
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(Duration.ofDays(7))))
            .signWith(key)
            .compact()
    }

    fun extractEmailClaims(token: String): String {
        return userFacade.getUserById(extractAllClaims(token).toLong()).email
    }

    fun extractIdClaims(token: String): Long {
        return userFacade.getUserById(extractAllClaims(token).toLong()).id
    }

    private fun extractAllClaims(token: String): String {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
                .subject
        } catch (e: Exception) {
            e.printStackTrace()
            TODO()
        }
    }
}