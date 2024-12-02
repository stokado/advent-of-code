private const val DAY = "DayTemplate"

fun main() {
    val input = readLines(DAY)
    val testInput = readLines("${DAY}_test")

    val part1Expected = 0
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

//    val part2Expected = 0
//    val part2Answer = part2(testInput)

//    partResults("Part 2", 0, part2(testInput)) { part2(input) }
}

private fun part1(input: List<String>): Int = TODO()
private fun part2(input: List<String>): Int = TODO()