package dev.all_things.common.extension

import org.apache.logging.log4j.Logger

public fun Exception.log(logger: Logger)
{
	logger.error(message, this)
}

public fun Exception.logWithSuppressed(logger: Logger)
{
	logger.error(message, this)

	suppressed.forEach { logger.error(it.message, it) }
}

