package libs

data class Bounds(val rowBounds: IntRange, val columnBounds: IntRange) {
    operator fun contains(position: Position): Boolean {
        return position.x in rowBounds && position.y in columnBounds
    }
}