package net.shadowfacts.krypton.markdown.pygments

import org.commonmark.renderer.html.HtmlRenderer

/**
 * @author shadowfacts
 */
object PygmentsExtension: HtmlRenderer.HtmlRendererExtension {

	override fun extend(builder: HtmlRenderer.Builder) {
		builder.nodeRendererFactory(::PygmentsCodeBlockRenderer)
	}

}