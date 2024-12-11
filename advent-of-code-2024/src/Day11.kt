private const val DAY = "Day11"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 55312
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

//    val part2Expected = 0
//    val part2Answer = part2(testInput)

//    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: List<String>): Int {
    var newStones = input
    for (i in 1..25) {
        newStones = blink(newStones)
    }
    return newStones.size
}

private fun part2(input: List<String>): Int = TODO()


private fun blink(stones: List<String>): List<String> {
    return buildList {
        for (stone in stones) {
            val engravedNumber = stone.toLong()

            if (engravedNumber == 0L) {
                add("1")
            } else if (stone.length % 2 == 0) {
                val leftStone = stone.substring(0, stone.length / 2)
                val rightStone = removeLeadingZeroes(stone.substring(stone.length / 2, stone.length))
                add(leftStone)
                add(rightStone)
            } else {
                val newValue = (engravedNumber * 2024).toString()
                add(newValue)
            }
        }
    }
}

private fun removeLeadingZeroes(str: String): String {
    val trimmed = str.trimStart { it == '0' }
    return trimmed.ifEmpty { "0" }
}

private fun readInput(name: String): List<String> = readText(name).split(" ")