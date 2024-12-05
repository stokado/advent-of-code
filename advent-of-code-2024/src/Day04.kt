private const val DAY = "Day04"

fun main() {
    val input = readLines(DAY)
    val testInput = readLines("${DAY}_test")

    val part1Expected = 18
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

    val part2Expected = 9
    val part2Answer = part2(testInput)

    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private val XMAS_REGEX = Regex("XMAS")

private fun part1(input: List<String>): Int {
    val leftToRight = input
    val rightToLeft = input.map { it.reversed() }

    val topToBottom = input.columns()
    val bottomToTop = topToBottom.map { it.reversed() }

    val BLtoTR = input.getAntiDiagonals()
    val TRtoBL = BLtoTR.map { it.reversed() }

    val TLtoBR = input.getDiagonals()
    val BRtoTL = TLtoBR.map { it.reversed() }

    val all = leftToRight + rightToLeft + topToBottom + bottomToTop +
        BLtoTR + TRtoBL + TLtoBR + BRtoTL

    return all.sumOf { XMAS_REGEX.findAll(it).count() }
}

private val MAS_REGEX = Regex("MAS|SAM")

private fun part2(rows: List<String>): Int {
    var mas = 0
    for (rowIndex in 1 until rows.lastIndex) {
        for (i in 1 until rows[rowIndex].lastIndex) {
            val diagonal = buildString {
                append(rows[rowIndex - 1][i - 1])
                append(rows[rowIndex][i])
                append(rows[rowIndex + 1][i + 1])
            }
            val antiDiagonal = buildString {
                append(rows[rowIndex - 1][i + 1])
                append(rows[rowIndex][i])
                append(rows[rowIndex + 1][i - 1])
            }

            if (MAS_REGEX.find(diagonal) != null && MAS_REGEX.find(antiDiagonal) != null) {
                mas++
            }
        }
    }
    return mas
}


private fun List<String>.columns(): List<String> {
    val rows = this
    return buildList {
        for (column in rows[0].indices)
            add(buildString {
                for (row in rows.indices) {
                    append(rows[row][column])
                }
            })
    }
}

private fun List<String>.getAntiDiagonals(): List<String> {
    val rows = this
    val width = rows.first().length
    val height = rows.size

    return buildList {
        for (y in rows.indices) {
            add(buildString {
                var cx = 0
                var cy = y
                do {
                    append(rows[cy][cx])
                    cx += 1
                    cy -= 1
                } while (cx < width && cy >= 0)
            })
        }
        for (x in rows[0].indices.drop(1)) {
            add(buildString {
                var cx = x
                var cy = height - 1
                do {
                    append(rows[cy][cx])
                    cx += 1
                    cy -= 1
                } while (cx < width && cy >= 0)
            })
        }
    }
}

private fun List<String>.getDiagonals(): List<String> {
    val rows = this
    val width = rows.first().length
    val height = rows.size

    return buildList {
        for (y in rows.indices.reversed()) {
            add(buildString {
                var cx = width - 1
                var cy = y
                do {
                    append(rows[cy][cx])
                    cx -= 1
                    cy -= 1
                } while (cx >= 0 && cy >= 0)
            })
        }
        for (x in rows[0].indices.reversed().drop(1)) {
            add(buildString {
                var cx = x
                var cy = height - 1
                do {
                    append(rows[cy][cx])
                    cx -= 1
                    cy -= 1
                } while (cx >= 0 && cy >= 0)
            })
        }
    }
}