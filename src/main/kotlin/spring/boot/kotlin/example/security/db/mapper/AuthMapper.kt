package spring.boot.kotlin.example.security.db.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import spring.boot.kotlin.example.security.db.entity.SecureUser

@Mapper
@Repository
interface AuthMapper {

    fun findByEmail(email: String): SecureUser

    fun register(secureUser: SecureUser)

    fun alreadyExists(email: String): Boolean

}