package dev.all_things.playground.app.web.rest

import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.lang.RuntimeException

@Controller
public class HelloWorldController
{
	private val logger = LogManager.getLogger(HelloWorldController::class.java)

	@GetMapping(path = ["/hello/{name}"])
	public fun sayHello(@PathVariable(name = "name") name: String): ResponseEntity<String>
	{
		if (name != "Neo")
		{
			throw RuntimeException("Only 'Neo' is welcome!")
		}

		return ResponseEntity.ok("Hello $name!")
	}


}