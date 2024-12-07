import libs.*
import libs.Direction.*
import libs.Direction.Companion.nextInDirection

private const val DAY = "Day06"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 41
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

    val part2Expected = 6
    val part2Answer = part2(testInput)

    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: Input): Int {
    val seenLocation = mutableSetOf<Position>()
    guardWalk(input) { (position, _), _ -> seenLocation.add(position) }

    return seenLocation.size
}

private fun part2(input: Input): Int {
    var possibleNewObstacles = 0
    return possibleNewObstacles
}


private fun guardWalk(
    input: Input,
    start: Position = input.start,
    initialDirection: Direction = NORTH,
    obstacles: Set<Position> = input.obstacles,
    onEachStep: (State, State) -> Unit = { _, _ -> },
) {
    var position = start
    var direction = initialDirection

    while (position in input.bounds) {
        val previousDirection = direction
        var nextPosition = position.nextInDirection(direction)

        while (nextPosition in obstacles) {
            direction = direction.turnRight()
            nextPosition = position.nextInDirection(direction)
        }

        onEachStep(position to previousDirection, nextPosition to direction)
        position = nextPosition
    }
}

private fun readInput(name: String): Input {
    val lines = readLines(name)
    val obstacles = mutableSetOf<Position>()
    var start: Position? = null

    for ((row, line) in lines.withIndex()) {
        for ((column, char) in line.withIndex()) {
            when (char) {
                '#' -> obstacles += Position(row, column)
                '^' -> start = Position(row, column)
            }
        }
    }

    return Input(
        start = start!!,
        bounds = Bounds(lines.indices, lines.first().indices),
        obstacles = obstacles
    )
}

private typealias State = Pair<Position, Direction>

private data class Input(
    val start: Position,
    val obstacles: Set<Position>,
    val bounds: Bounds
)

private data class Bounds(val rowBounds: IntRange, val columnBounds: IntRange) {
    operator fun contains(position: Position): Boolean {
        return position.x in rowBounds && position.y in columnBounds
    }
}