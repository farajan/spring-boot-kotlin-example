package spring.boot.kotlin.example.security.utility

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import spring.boot.kotlin.example.security.db.entity.SecureUser

open class MyUserDetails(secureUser: SecureUser) : SecureUser(secureUser), UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun isEnabled(): Boolean = super.enabled

    override fun getUsername(): String = super.email!!

    override fun getPassword(): String = super.passwd!!

    override fun isCredentialsNonExpired(): Boolean = super.credentialsNonExpired

    override fun isAccountNonExpired(): Boolean = super.accountNonExpired

    override fun isAccountNonLocked(): Boolean = super.accountNonLocked

}