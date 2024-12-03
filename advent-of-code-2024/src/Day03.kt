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

private fun part1(input: List<List<Pair<Int, Int>>>): Int =
    input.sumOf { line -> line.sumOf { it.pairMul() } }

private fun part2(input: List<String>): Int = TODO()

private fun Pair<Int, Int>.pairMul() = this.first * this.second

private fun readInput(name: String) = readLines(name) { str ->
    val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
    regex.findAll(str)
        .map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }
        .toList()
}