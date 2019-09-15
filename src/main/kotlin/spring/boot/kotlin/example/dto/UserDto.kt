package spring.boot.kotlin.example.dto

import spring.boot.kotlin.example.db.entity.Car
import spring.boot.kotlin.example.db.entity.User
import spring.boot.kotlin.example.db.enums.Sex
import java.time.LocalDateTime

data class UserDto(
        val id_user: Long,

        val firstName: String,

        val lastName: String,

        val email: String,

        val birthday: LocalDateTime?,

        val sex: Sex?,

        val phone: String?,

        val photo: String?,

        val cars: List<Car> = mutableListOf()
) {

    companion object{
        fun create(user: User) = UserDto(
                id_user = user.id_user!!,
                firstName = user.firstName!!,
                lastName = user.lastName!!,
                email = user.email!!,
                birthday = user.birthday,
                sex = user.sex,
                phone = user.phone,
                photo = user.photo,
                cars = user.cars
        )
    }

}