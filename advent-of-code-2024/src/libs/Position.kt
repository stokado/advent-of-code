package libs

data class Position(val x: Int, val y: Int) {
    fun offsetBy(x: Int = 0, y: Int = 0): Position = Position(this.x + x, this.y + y)

    companion object {
        val None = Position(-1, -1)
    }
}

operator fun <T> Matrix<T>.get(position: Position): T = this[position.x, position.y]

operator fun <T> Matrix<T>.set(position: Position, value: T) {
    this[position.x, position.y] = value
}
