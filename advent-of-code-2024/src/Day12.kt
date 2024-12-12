import libs.*
import libs.Direction.Companion.nextInDirection

private const val DAY = "Day12"

fun main() {
    fun testInput(n: Int) = readInput("${DAY}_test$n")
    fun input() = readInput(DAY)

    "Part 1" {
        part1(testInput(1)) shouldBe 140
        part1(testInput(2)) shouldBe 772
        part1(testInput(3)) shouldBe 1930
        measureAnswer { part1(input()) }
    }

//    val part2Expected = 0
//    val part2Answer = part2(testInput)

//    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: Matrix<Char>): Int {
    val visited = mutableSetOf<Position>()
    var result = 0

    for (row in input.rowIndices) {
        for (column in input.columnIndices) {
            val position = Position(row, column)
            if (position !in visited) {
                val (area, perimeter) = input.countAreaAndPerimeter(position, visited)
                result += area * perimeter
            }
        }
    }
    return result
}

private fun part2(input: List<String>): Int = TODO()

private fun Matrix<Char>.countAreaAndPerimeter(position: Position, visited: MutableSet<Position>): Pair<Int, Int> {
    var area = 1
    var perimeter = 0
    val fruit = this[position]
    visited.add(position)
    Direction.orthogonal.forEach { direction ->
        val nextPosition = position.nextInDirection(direction)
        if (nextPosition !in bounds || this[nextPosition] != fruit) {
            perimeter += 1
        } else if (nextPosition !in visited) {
            val (nextArea, nextPerimeter) = countAreaAndPerimeter(nextPosition, visited)
            area += nextArea
            perimeter += nextPerimeter
        }
    }
    return area to perimeter
}

private fun readInput(name: String): Matrix<Char> = readMatrix(name)
