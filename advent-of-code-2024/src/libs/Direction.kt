package libs

enum class Direction(private val dRow: Int, private val dCol: Int) {
    NORTH(-1, 0),
    EAST(0, 1),
    SOUTH(1, 0),
    WEST(0, -1);

    companion object {
        fun Position.nextInDirection(direction: Direction) = offsetBy(direction.dRow, direction.dCol)
    }

    fun turnRight(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
}