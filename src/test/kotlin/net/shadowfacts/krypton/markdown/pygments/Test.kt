package net.shadowfacts.krypton.markdown.pygments

import net.shadowfacts.krypton.Krypton
import net.shadowfacts.krypton.config.Configuration
import net.shadowfacts.krypton.markdown.StageRenderMarkdown
import net.shadowfacts.krypton.markdown.config.MarkdownConfig
import net.shadowfacts.krypton.markdown.config.markdown
import net.shadowfacts.krypton.pipeline.selector.PipelineSelectorExtension
import net.shadowfacts.krypton.util.dependencies.Dependencies
import java.io.File

/**
 * @author shadowfacts
 */
fun main(args: Array<String>) {
	val krypton = Krypton(Configuration {
		source = File("source")
		output = File("output")

		markdown = MarkdownConfig().apply {
			extraExtensions.add(PygmentsExtension)
		}
	})

	krypton.createPipeline {
		selector = PipelineSelectorExtension("md")
		addStage(StageRenderMarkdown(), Dependencies {
		})
	}

	krypton.generate()
}