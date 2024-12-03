private const val DAY = "Day03"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 161
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

//    val part2Expected = 0
//    val part2Answer = part2(testInput)

//    partResults("Part 2", 0, part2(testInput)) { part2(input) }
}

private val REGEX_PART1 = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

private fun MatchResult.mul() = groupValues[1].toInt() * groupValues[2].toInt()

private fun part1(input: String): Int = REGEX_PART1.findAll(input).sumOf { it.mul() }

private fun part2(input: List<String>): Int = TODO()

private fun readInput(name: String) = readLines(name).joinToString("")