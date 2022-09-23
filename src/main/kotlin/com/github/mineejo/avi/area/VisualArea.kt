package com.github.mineejo.avi.area

enum class VisualArea(val content: String) {
    ID("id: "),
    FILE("file: "),
    LINES("lines: "),
    CONTENT("content: "),
}

enum class VisualAreaLine(val content: String) {
    SEPARATOR("-")
}
