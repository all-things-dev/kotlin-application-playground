package dev.all_things.playground.common.web.rest.model

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement
import org.springframework.http.HttpStatus

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response<T : Any>(var code: String, var message: String, result: T? = null, results: List<T>? = null)
{
	// public val code = code
	// public val message = message
	public var result: T? = null
	public var results: List<T>? = null

	constructor(status: HttpStatus = HttpStatus.OK) :
			this(status.value().toString(), status.reasonPhrase)

	constructor(status: HttpStatus = HttpStatus.OK, message: String) :
			this(status.value().toString(), message)

	constructor(status: HttpStatus = HttpStatus.OK, result: T) :
			this(status.value().toString(), status.reasonPhrase, result)

	constructor(status: HttpStatus = HttpStatus.OK, results: List<T>) :
			this(status.value().toString(), status.reasonPhrase, results = results)
}