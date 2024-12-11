private const val DAY = "Day11"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 55312L
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

    val part2Expected = 65_601_038_650_482L
    val part2Answer = part2(testInput)

    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: List<Long>): Long = blink(input, 25)
private fun part2(input: List<Long>): Long = blink(input, 75)


private fun blink(stones: List<Long>, totalBlinks: Int): Long {
    data class State(val stone: Long, val step: Int)

    val cache = mutableMapOf<State, Long>()
    fun dfs(stone: Long, step: Int): Long {
        if (step == totalBlinks) {
            return 1L
        }

        val state = State(stone, step)
        if (state in cache) {
            return cache[state]!!
        }

        val result = when {
            stone == 0L -> dfs(1, step + 1)
            stone.toString().length % 2 == 0 -> {
                val string = stone.toString()
                val middle = string.length / 2

                val leftStone = string.substring(0..<middle).toLong()
                val rightStone = string.substring(middle).toLong()

                dfs(leftStone, step + 1) + dfs(rightStone, step + 1)
            }
            else -> dfs(stone * 2024, step + 1)
        }
        cache[state] = result
        return result
    }

    return stones.sumOf { stone -> dfs(stone, 0) }
}


private fun readInput(name: String): List<Long> = readText(name).splitLongs()