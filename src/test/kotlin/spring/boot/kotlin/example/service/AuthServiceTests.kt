package spring.boot.kotlin.example.service

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.security.crypto.password.PasswordEncoder
import spring.boot.kotlin.example.security.db.entity.SecureUser
import spring.boot.kotlin.example.security.db.mapper.AuthMapper
import spring.boot.kotlin.example.security.service.AuthService
import java.security.Principal
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class AuthServiceTests {

    @InjectMocks
    lateinit var authService: AuthService

    @Mock
    lateinit var authMapper: AuthMapper

    @Mock
    lateinit var principal: Principal

    @Mock
    lateinit var encoder: PasswordEncoder

    @Test
    fun register_shouldPass() {
        val secureUser = SecureUser(1L, "firstName", "lastName", "email", "password", LocalDateTime.now())
        authService.register(secureUser)
        Mockito.verify(authMapper, Mockito.times(1)).register(secureUser)
    }

    @Test
    fun getLoggedUser_shouldPass() {
        `when`(principal.name).thenReturn("test@test.test")
        authService.getLoggedUser(principal)
        Mockito.verify(authMapper, Mockito.times(1)).findByEmail(principal.name)
    }

}