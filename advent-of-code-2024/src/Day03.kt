private const val DAY = "Day03"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 161
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

    val testInput2 = readInput("${DAY}_test2")
    val part2Expected = 48
    val part2Answer = part2(testInput2)

    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private val REGEX_PART1 = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

private fun MatchResult.mul() = groupValues[1].toInt() * groupValues[2].toInt()

private fun part1(input: String): Int = REGEX_PART1.findAll(input).sumOf { it.mul() }

private val REGEX_PART2 = """don't\(\)|do\(\)|mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

private fun part2(input: String): Int {
    var doIt = true
    var sum = 0
    REGEX_PART2.findAll(input).forEach { match ->
        when (match.value) {
            "do()" -> doIt = true
            "don't()" -> doIt = false
            else -> if (doIt) {
                sum += match.mul()
            }
        }
    }
    return sum
}

private fun readInput(name: String) = readLines(name).joinToString("")