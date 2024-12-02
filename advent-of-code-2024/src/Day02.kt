import kotlin.math.*

private const val DAY = "Day02"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 2
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

    val part2Expected = 4
    val part2Answer = part2(testInput)

    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: List<List<Int>>): Int = input.count { it.isSafe() }

private fun part2(input: List<List<Int>>): Int = input.count { report ->
    val unsafeIndex = report.indexOfUnsafeChange()
    var isSafe = unsafeIndex == Int.MIN_VALUE
    var indexToRemove = (unsafeIndex - 1).coerceAtLeast(0)
    while (!isSafe && indexToRemove <= unsafeIndex + 1) {
        isSafe = report.removeAt(indexToRemove).isSafe()
        indexToRemove++
    }
    isSafe
}

private fun List<Int>.isSafe(): Boolean = indexOfUnsafeChange() == Int.MIN_VALUE

private fun List<Int>.indexOfUnsafeChange(): Int {
    var sign = 0
    for (i in 0 until lastIndex) {
        val diff = get(i) - get(i + 1)
        if (diff == 0 || abs(diff) > 3 || sign != diff.sign && sign != 0) {
            return i
        }
        sign = diff.sign
    }
    return Int.MIN_VALUE
}

private fun List<Int>.removeAt(index: Int) = this.toMutableList().apply { removeAt(index) }

private fun readInput(name: String) = readLines(name) { it.splitIntegers() }
