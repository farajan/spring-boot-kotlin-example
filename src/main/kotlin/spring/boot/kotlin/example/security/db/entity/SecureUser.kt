package spring.boot.kotlin.example.security.db.entity

import com.fasterxml.jackson.annotation.JsonSetter
import java.time.LocalDateTime

open class SecureUser(
        var id_user: Long? = null,

        var firstName: String? = null,

        var lastName: String? = null,

        var email: String? = null,

        @JsonSetter("password")
        var passwd: String? = null,

        var birthday: LocalDateTime? = null,

        var photo: String? = null,

        var version: Int = 0,

        var accountNonExpired: Boolean = true,

        var accountNonLocked: Boolean = true,

        var credentialsNonExpired: Boolean = true,

        var enabled: Boolean = true
) {

    constructor(secureUser: SecureUser) : this(
            secureUser.id_user,
            secureUser.firstName,
            secureUser.lastName,
            secureUser.email,
            secureUser.passwd,
            secureUser.birthday,
            secureUser.photo,
            secureUser.version,
            secureUser.accountNonExpired,
            secureUser.accountNonLocked,
            secureUser.credentialsNonExpired,
            secureUser.enabled) {
                id_user = secureUser.id_user
                firstName = secureUser.firstName
                lastName = secureUser.lastName
                email = secureUser.email
                passwd = secureUser.passwd
                birthday = secureUser.birthday
                photo = secureUser.photo
                version = secureUser.version
                accountNonExpired = secureUser.accountNonExpired
                accountNonLocked = secureUser.accountNonLocked
                credentialsNonExpired = secureUser.credentialsNonExpired
                enabled = secureUser.enabled

    }

}