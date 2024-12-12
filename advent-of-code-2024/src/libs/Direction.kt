package libs

enum class Direction(val dRow: Int, val dCol: Int) {
    NORTH(-1, 0),
    NORTH_EAST(-1, 1),
    EAST(0, 1),
    SOUTH_EAST(1, 1),
    SOUTH(1, 0),
    SOUTH_WEST(1, -1),
    WEST(0, -1),
    NORTH_WEST(-1, -1);

    companion object {
        fun Position.nextInDirection(direction: Direction) = offsetBy(direction.dRow, direction.dCol)
        val orthogonal: List<Direction> = listOf(NORTH, SOUTH, EAST, WEST)
        val diagonal: List<Direction> = listOf(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST)
    }

    fun turn45(clockwise: Boolean): Direction = turn(steps = 1, clockwise)
    fun turn90(clockwise: Boolean): Direction = turn(steps = 2, clockwise)

    private fun turn(steps: Int, clockwise: Boolean): Direction {
        val newDirectionOrdinal = (if (clockwise) ordinal + steps else ordinal - steps).mod(entries.size)
        return entries[newDirectionOrdinal]
    }
}

fun Position.nextBy(direction: Direction): Position = offsetBy(direction.dRow, direction.dCol)