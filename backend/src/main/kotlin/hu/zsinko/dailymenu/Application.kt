package hu.zsinko.dailymenu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.cache.annotation.EnableCaching
import java.util.logging.Logger


@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(MenuProperties::class)
class Application: SpringBootServletInitializer() {
    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(Application::class.java)
    }
}

@ConfigurationProperties("menu")
class MenuProperties {
    lateinit var facebooktoken: String
}


fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

inline fun <reified T:Any> loggerFor(): Logger = Logger.getLogger(T::class.qualifiedName)