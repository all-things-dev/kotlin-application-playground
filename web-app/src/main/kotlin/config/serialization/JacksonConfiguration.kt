package config.serialization

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter
import java.util.*

@Configuration
class JacksonConfiguration
{
	@Bean(name = ["objectMapper", "jsonMapper"])
	fun jsonMapper(): JsonMapper
	{
		return createJsonMapper()
	}

	@Bean(name = ["xmlMapper"])
	fun xmlMapper(): XmlMapper
	{
		return createXmlMapper()
	}

	@Bean(name = ["mappingJackson2HttpMessageConverter"])
	fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter
	{
		return MappingJackson2HttpMessageConverter(createJsonMapper())
	}

	@Bean("mappingJackson2XmlHttpMessageConverter")
	fun mappingJackson2XmlHttpMessageConverter(): MappingJackson2XmlHttpMessageConverter
	{
		return MappingJackson2XmlHttpMessageConverter(createXmlMapper())
	}

	companion object
	{
		private fun createJsonMapper(): JsonMapper
		{
			// Configuring JSON mapper
			return configureMapper(JsonMapper())
		}

		private fun createXmlMapper(): XmlMapper
		{
			// Configuring XML mapper
			val xmlMapper = configureMapper(XmlMapper())

			// Enabling serialization to XML
			xmlMapper.registerModule(JacksonXmlModule())

			return xmlMapper
		}

		private fun <T : ObjectMapper> configureMapper(mapper: T): T
		{
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			mapper.disable(SerializationFeature.INDENT_OUTPUT)
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

			mapper.registerModule(JakartaXmlBindAnnotationModule())
			mapper.registerModule(Jdk8Module())
			mapper.registerModule(JavaTimeModule())

			mapper.setTimeZone(TimeZone.getDefault())

			return mapper
		}
	}
}
