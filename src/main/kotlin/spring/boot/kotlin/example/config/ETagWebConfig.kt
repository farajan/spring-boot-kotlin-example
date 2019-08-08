package spring.boot.kotlin.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.ShallowEtagHeaderFilter

@Configuration
class ETagWebConfig {

    @Bean
    fun shallowEtagHeaderFilter() = ShallowEtagHeaderFilter()

}