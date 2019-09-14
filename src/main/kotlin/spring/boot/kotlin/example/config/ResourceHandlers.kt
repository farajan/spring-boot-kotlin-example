package spring.boot.kotlin.example.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ResourceHandlers : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val classpath_resource_locations : String = {
            "classpath:/META-INF/resources/"
            "classpath:/resources/"
            "classpath:/static/"
            "classpath:/public/"
            "/Users/Jan/IdeaProjects/spring-boot-kotlin-example/src/main/resources/images/"
        }.toString()
        registry.addResourceHandler("/**").addResourceLocations(classpath_resource_locations)
    }
}