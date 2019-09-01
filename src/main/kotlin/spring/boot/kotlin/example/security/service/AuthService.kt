package spring.boot.kotlin.example.security.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import spring.boot.kotlin.example.security.db.mapper.AuthMapper
import spring.boot.kotlin.example.security.db.entity.SecureUser
import java.security.Principal

@Service
class AuthService(
        @Autowired private val authMapper: AuthMapper,
        @Autowired private val encoder: PasswordEncoder
) {

    @CacheEvict("userCache", allEntries = true)
    fun register(secureUser: SecureUser): SecureUser {
        if(secureUser.firstName == null
                || secureUser.lastName == null
                || secureUser.email == null
                || secureUser.passwd == null
        ) throw NullPointerException("Some of following mandatory parameters are null, see: " +
                "firstName=${secureUser.firstName}, " +
                "lastName=${secureUser.lastName}, " +
                "email=${secureUser.email}, " +
                "password=${secureUser.passwd}, " +
                "birthday=${secureUser.birthday}")
        if(alreadyExists(secureUser)) throw IllegalArgumentException("User with this email already exists.")
        secureUser.passwd = encoder.encode(secureUser.passwd)
        authMapper.register(secureUser)
        return secureUser
    }

    fun getLoggedUser(principal: Principal): SecureUser {
        val secureUser = authMapper.findByEmail(principal.name)
        secureUser.passwd = null
        return secureUser
    }

    private fun alreadyExists(secureUser: SecureUser): Boolean = authMapper.alreadyExists(secureUser.email!!)

}