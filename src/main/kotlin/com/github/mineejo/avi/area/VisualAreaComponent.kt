package com.github.mineejo.avi.area

open class VisualAreaComponent {
	private val areaFileIndex: Int = 1
	private val areaLinesIndex: Int = 2
	private val areaContentIndex: Int = 3

	protected val keyId: String = "id: "
	protected val keyFile: String = "file: "
	protected val keyLines: String = "lines: "
	protected val keyContent: String = "content: "
	protected val keySeparator: String = "-"

	protected fun forEach(
		areas: MutableList<String>,
		function: (id: String, file: String, lines: String, content: String) -> Unit
	) {
		for ((index, areaId) in areas.withIndex()) {
			if (!areaId.startsWith(keyId)) continue

			val areaFile = areas.getOrNull(index + areaFileIndex) ?: ""
			val areaLines = areas.getOrNull(index + areaLinesIndex) ?: ""
			val areaContent = areas.getOrNull(index + areaContentIndex) ?: ""

			when {
				(!areaFile.startsWith(keyFile)) -> continue
				(!areaLines.startsWith(keyLines)) -> continue
				(!areaContent.startsWith(keyContent)) -> continue
			}

			function(areaId, areaFile, areaLines, areaContent)
		}
	}
}
