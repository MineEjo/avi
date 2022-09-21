package com.github.mineejo.avi.enums

enum class Area(val content: String) {
    ID("id: "),
    FILE("file: "),
    LINES("lines: "),
    CONTENT("content: "),
}

enum class AreaLine(val content: String) {
    SEPARATOR("-")
}