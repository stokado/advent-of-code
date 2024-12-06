import libs.*
import libs.Direction.*
import libs.Direction.Companion.nextInDirection

private const val DAY = "Day06"

fun main() {
    val input = readMatrix(DAY)
    val testInput = readMatrix("${DAY}_test")

    val part1Expected = 41
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

//    val part2Expected = 0
//    val part2Answer = part2(testInput)

//    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: Matrix<Char>): Int {
    val guard = Guard(input)
    guard.walk()

    var sum = 0
    for (i in input.rowIndices) {
        for (j in input.columnIndices) {
            if (input[i, j] == 'X') {
                sum++
            }
        }
    }

    return sum
}

private fun part2(input: List<String>): Int = TODO()

private class Guard(val room: Matrix<Char>) {
    var position: Position = room.find('^')
    var direction: Direction = NORTH

    fun walk() {
        while (true) {
            room[position] = 'X'
            val nextPosition = position.nextInDirection(direction)
            if (nextPosition.x !in room.columnIndices || nextPosition.y !in room.rowIndices) {
                break
            }
            if (room[nextPosition] == '#') {
                direction = direction.turnRight()
                continue
            }
            position = nextPosition
        }
    }
}