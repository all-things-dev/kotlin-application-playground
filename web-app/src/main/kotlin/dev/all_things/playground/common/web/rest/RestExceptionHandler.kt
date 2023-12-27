package dev.all_things.playground.common.web.rest

import dev.all_things.common.extension.createResponse
import dev.all_things.common.extension.logWithSuppressed
import dev.all_things.playground.common.i18n.InternationalizationService
import dev.all_things.playground.common.web.rest.model.Response
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
public class RestExceptionHandler(val i18n: InternationalizationService) : ResponseEntityExceptionHandler()
{
	private val exceptionLogger = LogManager.getLogger(RestExceptionHandler::class.java)

	@ExceptionHandler(Exception::class)
	public fun processException(e: Exception): ResponseEntity<Response<Any>>
	{
		e.logWithSuppressed(exceptionLogger)

		return INTERNAL_SERVER_ERROR.createResponse(i18n.getMessage("common.rest.http_500"))
	}
}