package kr.hs.dgsw.vroom.global.security.jwt

import kr.hs.dgsw.vroom.common.error.VroomException
import kr.hs.dgsw.vroom.global.security.auth.AuthDetailService
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(
    private val jwtProvider: JwtProvider,
    private val authDetailService: AuthDetailService
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val bearerToken: String? = getTokenFromHeader(request.getHeader(HttpHeaders.AUTHORIZATION))
            if (bearerToken != null) {
                val id: Long = jwtProvider.extractIdClaims(bearerToken)
                val userDetails: UserDetails = authDetailService.loadUserByUsername(id.toString())
                val authentication: Authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: VroomException) {
            filterException(e, response)
        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenFromHeader(headerValue: String?): String? {
        return if (headerValue != null && headerValue.startsWith("Bearer ")) {
            headerValue.replace("Bearer ", "")
        } else null
    }

    @Throws(IOException::class)
    private fun filterException(exception: VroomException, response: HttpServletResponse) {
        response.status = exception.status
        response.characterEncoding = "UTF-8"
        response.contentType = "application/json"
        response.writer.write(exception.message)
    }

}