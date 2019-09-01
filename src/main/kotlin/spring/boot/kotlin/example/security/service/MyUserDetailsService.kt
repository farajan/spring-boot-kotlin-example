package spring.boot.kotlin.example.security.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import spring.boot.kotlin.example.security.db.mapper.AuthMapper
import spring.boot.kotlin.example.security.utility.MyUserDetails
import spring.boot.kotlin.example.security.db.entity.SecureUser


@Service
class MyUserDetailsService(@Autowired private val authMapper: AuthMapper) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(email: String): UserDetails {
        val secureUser: SecureUser? = authMapper.findByEmail(email)
        if(secureUser != null) return MyUserDetails(secureUser) else throw UsernameNotFoundException(email)
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder(11)
    }


}