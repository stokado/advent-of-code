import libs.*
import libs.Direction.Companion.nextInDirection

private const val DAY = "Day10"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 36
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

    val part2Expected = 81
    val part2Answer = part2(testInput)

    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: Map<Position, Int>): Int = trailheadScores(input, false)
private fun part2(input: Map<Position, Int>): Int = trailheadScores(input, true)

private fun trailheadScores(input: Map<Position, Int>, findAll: Boolean): Int {
    var result = 0
    for (cell in input) {
        if (cell.value == 0) {
            val trailheadScore = if (findAll) {
                dfs(input, cell.key).toList().size
            } else {
                dfs(input, cell.key).toSet().size
            }
            result += trailheadScore
        }
    }
    return result
}

private fun dfs(cells: Map<Position, Int>, cell: Position): Sequence<Position> = sequence {
    if (cells[cell] == 9) {
        yield(cell)
    }
    Direction.orthogonal.forEach { direction ->
        val newCell = cell.nextInDirection(direction)
        if (newCell in cells && (cells[newCell] ?: 0) - 1 == cells[cell]) {
            yieldAll(dfs(cells, newCell))
        }
    }
}

private fun readInput(name: String): Map<Position, Int> {
    val input = readLines(name)

    return buildMap {
        for ((row, line) in input.withIndex()) {
            for ((col, char) in line.withIndex()) {
                put(Position(row, col), char.digitToInt())
            }
        }
    }
}