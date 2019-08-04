package spring.boot.kotlin.example.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spring.boot.kotlin.example.service.UserService

@RestController
@RequestMapping("/user")
class UserController(@Autowired private val userService: UserService) {

}