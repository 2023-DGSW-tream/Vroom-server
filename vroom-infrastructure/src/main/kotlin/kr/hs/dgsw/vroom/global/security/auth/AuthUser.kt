package kr.hs.dgsw.vroom.global.security.auth

import kr.hs.dgsw.vroom.domain.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthUser(
    val user: User
): UserDetails{

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return setOf(GrantedAuthority { user.role.name })
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.accountId
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}