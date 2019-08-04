package spring.boot.kotlin.example.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import spring.boot.kotlin.example.db.mapper.UserMapper

@Service
class UserService(@Autowired private val userMapper: UserMapper) {

}