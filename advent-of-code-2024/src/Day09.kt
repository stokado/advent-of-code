import libs.*

private const val DAY = "Day09"

fun main() {
    fun input() = readInput(DAY)
    fun testInput() = readInput("${DAY}_test")

    "Part 1" {
        part1(testInput()) shouldBe 1928
        measureAnswer { part1(input()) }
    }

//    "Part 2" {
//        part2(testInput()) shouldBe 2858
//        measureAnswer { part2(input()) }
//    }
}

private fun part1(input: Pair<List<Int>, List<Int>>): Long {
    val (files, freeSpaces) = input

    var position = 0
    var left = 0
    var right = files.lastIndex
    var pendingFileSize = 0
    var checkSum = 0L

    fun placeFile(fileId: Int, fileSize: Int) {
        checkSum += checksumOf(position, fileId, fileSize)
        position += fileSize
    }

    while (left < right) {
        placeFile(fileId = left, fileSize = files[left])

        var freeSpace = freeSpaces[left]
        while (freeSpace > 0 && left < right) {
            val moveFilesSize = if (pendingFileSize != 0) pendingFileSize else files[right]
            freeSpace -= moveFilesSize

            pendingFileSize = (-freeSpace).coerceAtLeast(0)
            placeFile(fileId = right, fileSize = moveFilesSize - pendingFileSize)

            if (pendingFileSize == 0) {
                right--
            }
        }
        left++
    }
    if (pendingFileSize > 0) {
        placeFile(fileId = right, fileSize = pendingFileSize)
    }

    return checkSum
}

private fun part2(input: List<String>): Int = TODO()

private fun checksumOf(startIndex: Int, fileId: Int, fileSize: Int): Long {
    return (startIndex rangeOfSize fileSize).sumOf { index -> (fileId * index).toLong() }
}

private fun readInput(name: String) = readText(name)
    .map(Char::digitToInt)
    .splitByParity()