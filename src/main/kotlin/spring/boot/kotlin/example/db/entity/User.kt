package spring.boot.kotlin.example.db.entity

import spring.boot.kotlin.example.db.enums.Sex
import java.time.LocalDateTime

data class User (

        val id_user: Long? = null,

        val firstName: String? = null,

        val lastName: String? = null,

        val email: String? = null,

        val password: String? = null,

        val birthday: LocalDateTime? = null,

        val sex: Sex? = null,

        val phone: String? = null,

        val photo: String? = null,

        val cars: List<Car> = mutableListOf()

)