package dev.all_things.common.extension

import dev.all_things.playground.common.web.rest.model.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

public fun HttpStatus.createResponse(): ResponseEntity<Response<Any>>
{
	return ResponseEntity.status(this).body(Response(this))
}

public fun HttpStatus.createResponse(message:String): ResponseEntity<Response<Any>>
{
	return ResponseEntity.status(this).body(Response(this, message))
}