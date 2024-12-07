private const val DAY = "Day07"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 3749L
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

//    val part2Expected = 0
//    val part2Answer = part2(testInput)

//    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: List<Equation>): Long {
    return input.filter { isValidEquation(it.numbers, it.rule, listOf(Long::plus, Long::times)) }
        .sumOf { it.rule }
}

private fun part2(input: List<String>): Int = TODO()


private data class Equation(val rule: Long, val numbers: List<Long>)
private typealias Operation = (Long, Long) -> Long

private fun isValidEquation(numbers: List<Long>, testValue: Long, operations: List<Operation>): Boolean {
    fun test(index: Int, value: Long): Boolean = when {
        index == numbers.lastIndex -> testValue == value
        value > testValue -> false
        else -> operations.any { operation -> test(index + 1, operation(value, numbers[index + 1])) }
    }

    return test(index = 0, value = numbers[0])
}

private fun readInput(name: String): List<Equation> = readLines(name) { line ->
    Equation(
        line.substringBefore(":").toLong(),
        line.substringAfter(": ").splitLongs()
    )
}
