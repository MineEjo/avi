package com.github.mineejo.avi.area

open class VisualArea(
	areaId: String,
	areaFile: String,
	private val areaLines: String,
	areaContent: String
) : VisualAreaFields() {

	private fun processLines(): IntRange {
		val lines: String = areaLines.replace(keyLines, "")
		val start: Int = lines.split(keySeparator)[0].toInt()
		val end: Int = lines.split(keySeparator)[1].toInt()
		return IntRange(start, end)
	}

	val id: String = areaId.replace(keyId, "")
	val file: String = areaFile.replace(keyFile, "")
	val lines: IntRange = processLines()
	val content: String = areaContent.replace(keyContent, "")
}
