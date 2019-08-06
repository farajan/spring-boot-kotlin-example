package spring.boot.kotlin.example.db.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import spring.boot.kotlin.example.db.entity.User

@Mapper
@Repository
interface UserMapper {

    fun findAll(): List<User>

    fun findById(id: Long): User

    fun create(user: User)

    fun alreadyExists(email: String): Boolean

    fun update(user: User)

    fun delete(id: Long)

    fun buyCar(id_user: Long, id_car: Long)

    fun sellCar(id_user: Long, id_car: Long)

    fun inProperty(id_user: Long, id_car: Long): Boolean
}