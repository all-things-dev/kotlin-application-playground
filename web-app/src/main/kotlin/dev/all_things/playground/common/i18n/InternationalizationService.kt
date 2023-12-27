package dev.all_things.playground.common.i18n

import org.apache.logging.log4j.LogManager
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
public class InternationalizationService(val messageSource: MessageSource)
{
	public fun getMessage(key: String, vararg args: Any): String
	{
		return messageSource.getMessage(key, args, LocaleContextHolder.getLocale())
	}

	public fun getMessage(key: String, locale: Locale, vararg args: Any): String
	{
		return messageSource.getMessage(key, args, locale)
	}
}