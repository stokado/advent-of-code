package libs

enum class Direction(val dRow: Int, val dCol: Int) {
    NORTH(-1, 0),
    EAST(0, 1),
    SOUTH(1, 0),
    WEST(0, -1);

    companion object {
        fun Position.nextInDirection(direction: Direction) = offsetBy(direction.dRow, direction.dCol)
        val orthogonal: List<Direction> = listOf(NORTH, SOUTH, EAST, WEST)
    }

    fun turnRight(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
}