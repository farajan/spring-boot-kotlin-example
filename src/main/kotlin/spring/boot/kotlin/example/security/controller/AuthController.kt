package spring.boot.kotlin.example.security.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import spring.boot.kotlin.example.security.service.AuthService
import spring.boot.kotlin.example.security.db.entity.SecureUser
import java.security.Principal

@RestController
@RequestMapping("/auth")
class AuthController(@Autowired private val authService: AuthService) {

    @PostMapping("/register")
    fun register(@RequestBody secureUser: SecureUser): SecureUser = authService.register(secureUser)

    @GetMapping("/getLoggedUser")
    fun getLoggedUser(principal: Principal): SecureUser = authService.getLoggedUser(principal)


}