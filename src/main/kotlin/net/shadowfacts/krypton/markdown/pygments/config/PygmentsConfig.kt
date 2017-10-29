package net.shadowfacts.krypton.markdown.pygments.config

import net.shadowfacts.krypton.config.Configuration
import net.shadowfacts.krypton.config.config

/**
 * @author shadowfacts
 */
var Configuration.pygmentsLineNumbers: Boolean by config(java.lang.Boolean::parseBoolean, fallback = { false })