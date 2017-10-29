package net.shadowfacts.krypton.markdown.pygments

import net.shadowfacts.krypton.Krypton
import org.commonmark.renderer.html.HtmlRenderer

/**
 * @author shadowfacts
 */
class PygmentsExtension(private val krypton: Krypton): HtmlRenderer.HtmlRendererExtension {

	override fun extend(builder: HtmlRenderer.Builder) {
		builder.nodeRendererFactory {
			PygmentsCodeBlockRenderer(it, krypton)
		}
	}

}