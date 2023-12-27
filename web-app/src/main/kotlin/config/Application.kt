package config

import org.apache.logging.log4j.LogManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = ["config", "dev.all_things"])
public class Application : SpringBootServletInitializer()
{
	private val logger = LogManager.getLogger(Application::class.java)

	override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder
	{
		return builder.sources(Application::class.java)
	}
}

fun main(args: Array<String>)
{
	runApplication<Application>(*args)
}

