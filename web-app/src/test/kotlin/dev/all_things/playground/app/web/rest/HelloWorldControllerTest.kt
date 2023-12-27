package dev.all_things.playground.app.web.rest

import dev.all_things.playground.common.i18n.InternationalizationService
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(HelloWorldController::class, InternationalizationService::class)
class HelloWorldControllerTest
{
	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun sayHelloInJson()
	{
		mockMvc.perform(get("/greet/Neo").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk)
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("\$.code").value("200"))
			.andExpect(jsonPath("\$.message").value("Hello, Neo!"))
	}

	@Test
	fun sayHelloInXml()
	{
		mockMvc.perform(get("/greet/Neo").accept(MediaType.APPLICATION_XML))
			.andExpect(status().isOk)
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
			.andExpect(xpath("Response/code").string("200"))
			.andExpect(xpath("Response/message").string(Matchers.equalTo("Hello, Neo!")))
	}
}