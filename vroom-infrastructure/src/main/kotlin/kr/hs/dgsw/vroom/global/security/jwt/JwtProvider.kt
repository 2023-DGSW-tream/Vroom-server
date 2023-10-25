package kr.hs.dgsw.vroom.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct


@Component
class JwtProvider {
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
            .setExpiration(Date.from(now.plus(Duration.ofSeconds(30))))
            .signWith(key)
            .compact()
    }

    private fun extractAllClaims(token: String): Claims? {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            TODO()
        }
    }
}