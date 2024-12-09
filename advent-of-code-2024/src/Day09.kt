private const val DAY = "Day09"

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test1")

    val part1Expected = 1928L
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

//    val part2Expected = 0
//    val part2Answer = part2(testInput)

//    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: List<MemoryPiece>): Long {
    var result = 0L

    for ((i, memory) in input.withIndex()) {
        if (memory.id == null) {
            break
        }
        val hash = i * memory.id
        result += hash
    }

    return result
}

private fun part2(input: List<String>): Int = TODO()

private data class MemoryPiece(
    val id: Int?
)

private fun readInput(name: String): List<MemoryPiece> {
    val inputRaw = readText(name)
    val input = mutableListOf<MemoryPiece>()
    var currentId = 0
    for ((i, char) in inputRaw.withIndex()) {
        val size = char.digitToInt()
        if (i % 2 == 0) {
            for (j in 0 until size) {
                input.add(
                    MemoryPiece(
                        id = currentId,
                    )
                )
            }
            currentId++
        } else {
            for (j in 0 until size) {
                input.add(MemoryPiece(null))
            }
        }
    }
    input.rearrangeMemory()
    return input
}

private fun MutableList<MemoryPiece>.rearrangeMemory() {
    var left = 1
    var right = this.lastIndex

    while (left <= right) {
        if (this[left].id == null && this[right].id != null) {
            this[left] = this[right]
            this[right] = MemoryPiece(null)
            left++
            right--
        }
        if (this[left].id != null) {
            left++
        }
        if (this[right].id == null) {
            right--
        }
    }
}