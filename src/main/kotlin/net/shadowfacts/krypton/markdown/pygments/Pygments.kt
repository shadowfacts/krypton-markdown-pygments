package net.shadowfacts.krypton.markdown.pygments

import org.python.util.PythonInterpreter

/**
 * @author shadowfacts
 */
object Pygments {

	private val interpreter = PythonInterpreter()

	init {
		interpreter.exec(
"""
from pygments import highlight
from pygments.lexers import (get_lexer_by_name, guess_lexer)
from pygments.formatters import HtmlFormatter
"""
		)
	}

	fun highlight(code: String, lexerName: String, linenos: Boolean): String {
		interpreter.set("code", code)
		interpreter.set("lexer_name", lexerName)
		interpreter.exec("result = highlight(code, get_lexer_by_name(lexer_name), ${createFormatter(linenos)})")
		return interpreter.get("result", String::class.java)
	}

	fun highlight(code: String, linenos: Boolean): String {
		interpreter.set("code", code)
		interpreter.exec("lexer = guess_lexer(code)\nresult=highlight(code, lexer, ${createFormatter(linenos)})")
		return interpreter.get("result", String::class.java)
	}

	private fun createFormatter(linenos: Boolean): String {
		return if (linenos) {
			"HtmlFormatter(linenos = \"table\")"
		} else {
			"HtmlFormatter(linenos = False)"
		}
	}

}