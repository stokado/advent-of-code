private const val DAY = "DayTemplate"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 0
    val part1Answer = part1(testInput)

    val part2Expected = 0
    val part2Answer = part2(testInput)

    check(part1Expected == part1Answer)
    check(part2Expected == part2Answer)
}

private fun part1(input: List<String>): Int = TODO()
private fun part2(input: List<String>): Int = TODO()