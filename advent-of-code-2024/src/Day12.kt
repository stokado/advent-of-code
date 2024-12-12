import libs.*

private const val DAY = "Day12"
private typealias Garden = Matrix<Char>

fun main() {
    fun testInput(n: Int) = readInput("${DAY}_test$n")
    fun input() = readInput(DAY)

    "Part 1" {
        part1(testInput(1)) shouldBe 140
        part1(testInput(2)) shouldBe 772
        part1(testInput(3)) shouldBe 1930
        measureAnswer { part1(input()) }
    }

    "Part 2" {
        part2(testInput(1)) shouldBe 80
        part2(testInput(2)) shouldBe 436
        part2(testInput(3)) shouldBe 1206
        measureAnswer { part2(input()) }
    }
}

private fun part1(input: Garden): Int = calculateRegionsPrice(input, RegionContext::countSides)
private fun part2(input: Garden): Int = calculateRegionsPrice(input, RegionContext::countCorners)

private fun calculateRegionsPrice(map: Garden, countFenceFunction: RegionContext.(Position) -> Int): Int {
    val visited = mutableSetOf<Position>()

    fun regionPrices(start: Position): Int {
        var area = 0
        var fences = 0
        val context = RegionContext(map, fruit = map[start])

        val queue = ArrayDeque<Position>()
        fun addNext(position: Position) {
            if (visited.add(position)) {
                queue += position
            }
        }

        addNext(start)
        while (queue.isNotEmpty()) {
            val position = queue.removeFirst()
            area += 1
            fences += context.countFenceFunction(position)

            Direction.orthogonal.asSequence()
                .map(position::nextBy)
                .filter(context::isInRegion)
                .forEach(::addNext)
        }
        return area * fences
    }

    return map.positions().filter { it !in visited }.sumOf { regionPrices(it) }
}

private fun RegionContext.countSides(position: Position): Int =
    Direction.orthogonal.count { !isInRegion(position.nextBy(it)) }

private fun RegionContext.countCorners(position: Position): Int = Direction.diagonal.count { diagonalDirection ->
    val leftPathInRegion = isInRegion(position.nextBy(diagonalDirection.turn45(clockwise = false)))
    val rightPathIsInRegion = isInRegion(position.nextBy(diagonalDirection.turn45(clockwise = true)))

    (!leftPathInRegion && !rightPathIsInRegion ||
        (leftPathInRegion && rightPathIsInRegion && !isInRegion(position.nextBy(diagonalDirection))))
}

private class RegionContext(val map: Garden, private val fruit: Char) {
    fun isInRegion(position: Position): Boolean {
        return map.getOrNull(position) == fruit
    }
}

private fun readInput(name: String): Garden = readMatrix(name)
