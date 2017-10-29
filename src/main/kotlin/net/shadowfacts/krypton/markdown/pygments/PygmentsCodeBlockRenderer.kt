package net.shadowfacts.krypton.markdown.pygments

import net.shadowfacts.krypton.Krypton
import net.shadowfacts.krypton.markdown.pygments.config.pygmentsLineNumbers
import org.commonmark.node.FencedCodeBlock
import org.commonmark.node.IndentedCodeBlock
import org.commonmark.node.Node
import org.commonmark.renderer.NodeRenderer
import org.commonmark.renderer.html.HtmlNodeRendererContext
import org.commonmark.renderer.html.HtmlWriter

/**
 * @author shadowfacts
 */
class PygmentsCodeBlockRenderer(private val html: HtmlWriter, private val krypton: Krypton): NodeRenderer {

	constructor(context: HtmlNodeRendererContext,krypton: Krypton): this(context.writer, krypton)

	override fun getNodeTypes() = setOf(FencedCodeBlock::class.java, IndentedCodeBlock::class.java)

	override fun render(node: Node) {
		val highlighted = when (node) {
			is FencedCodeBlock -> Pygments.highlight(node.literal, node.info, krypton.config.pygmentsLineNumbers)
			is IndentedCodeBlock -> Pygments.highlight(node.literal, krypton.config.pygmentsLineNumbers)
			else -> throw RuntimeException("Invalid node type, ${node::class}, expected either FencedCodeBlock or IndentedCodeBlock")
		}

		html.apply {
			line()
			raw(highlighted)
			line()
		}
	}

}