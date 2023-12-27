package dev.all_things.playground.app.web.rest

import dev.all_things.common.extension.createResponse
import dev.all_things.playground.common.web.rest.model.Response
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
public class HelloWorldController
{
	private val logger = LogManager.getLogger(HelloWorldController::class.java)

	@GetMapping(path = ["/greet/{name}"], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
	public fun sayHello(@PathVariable(name = "name") name: String): ResponseEntity<Response<String>>
	{
		if (name != "Neo")
		{
			throw RuntimeException("Only 'Neo' is welcome!")
		}

		return HttpStatus.OK.createResponse("Hello, $name!")
	}
}