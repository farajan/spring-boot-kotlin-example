package spring.boot.kotlin.example.security.basic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import spring.boot.kotlin.example.security.service.MyUserDetailsService

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        @Autowired private val authSuccess: AuthSuccess,
        @Autowired private val authFail: AuthFail,
        @Autowired private val myUserDetailsService: MyUserDetailsService,
        @Autowired private val encoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll()

                .and()
                .formLogin()
                .successHandler(authSuccess)
                .failureHandler(authFail)

                .and()
                .logout()
                .deleteCookies("JSESSIONID")

                .and()
                .csrf()
                .disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder)
    }
}