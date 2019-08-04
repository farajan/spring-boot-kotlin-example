package spring.boot.kotlin.example.db.entity

import java.time.LocalDateTime

data class User (

        val id_user: Long = -1,

        val firstName: String? = null,

        val lastName: String? = null,

        val email: String? = null,

        val password: String? = null,

        val birthday: LocalDateTime? = null,

        val photo: String? = null

)