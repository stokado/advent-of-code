import libs.*

private const val DAY = "Day08"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 14
    println(testInput)
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

//    val part2Expected = 0
//    val part2Answer = part2(testInput)

//    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: AntennaField): Int {
    val bounds = input.bounds
    val antennas = input.antennas
    val antiNodes = mutableSetOf<Position>()

    antennas.values.forEach { positions ->
        positions.forEach { position ->
            positions.forEach {
                if (it != position) {
                    val distance = position - it
                    val newAntiNodePosition = position + distance
                    if (newAntiNodePosition in bounds) {
                        antiNodes.add(newAntiNodePosition)
                    }
                }
            }
        }
    }

    return antiNodes.size
}

private fun part2(input: List<String>): Int = TODO()


private fun readInput(name: String): AntennaField {
    val lines = readLines(name)

    val mapping = mutableMapOf<Char, MutableSet<Position>>()

    for ((row, string) in lines.withIndex()) {
        for ((column, char) in string.withIndex()) {
            if (char != '.') {
                mapping.computeIfAbsent(char) { mutableSetOf() }.add(Position(row, column))
            }
        }
    }
    return AntennaField(
        antennas = mapping,
        bounds = Bounds(lines.indices, lines.first().indices)
    )
}

private data class AntennaField(val antennas: Map<Char, Set<Position>>, val bounds: Bounds)