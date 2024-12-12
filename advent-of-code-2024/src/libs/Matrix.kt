package libs

import printValue
import readLines

class Matrix<T>(lines: List<List<T>>) {
    val rowCount: Int = lines.size
    val lastRowIndex: Int = rowCount - 1
    val rowIndices: IntRange = 0..lastRowIndex

    val columnCount: Int = lines.first().size
    val lastColumnIndex: Int = columnCount - 1
    val columnIndices: IntRange = 0..lastColumnIndex
    val bounds = Bounds(rowIndices, columnIndices)

    private var values: MutableList<T> = MutableList(rowCount * columnCount) { i ->
        lines[i / columnCount][i % columnCount]
    }

    operator fun get(x: Int, y: Int): T = values[index(x, y)]

    operator fun set(x: Int, y: Int, value: T) {
        values[index(x, y)] = value
    }

    operator fun contains(position: Position): Boolean {
        return position in bounds
    }

    private fun index(x: Int, y: Int): Int = x * columnCount + y

    fun <T> find(value: T): Position {
        for (i in rowIndices) {
            for (j in columnIndices) {
                if (values[index(i, j)] == value) {
                    return Position(i, j)
                }
            }
        }
        return Position.None
    }
}

fun <T> List<List<T>>.toMatrix(): Matrix<T> = Matrix(this)

fun readMatrix(fileName: String): Matrix<Char> = readMatrix(fileName) { line -> line.map { it } }
fun <T> readMatrix(fileName: String, lineTransform: (String) -> List<T>): Matrix<T> =
    readLines(fileName, lineTransform).toMatrix()

/** Prints matrix row by row representing each element as a char. */
fun <T> Matrix<T>.debugPrint(transform: (position: Position, value: T) -> Char) {
    for (row in rowIndices) {
        columnIndices.joinToString(separator = "") { col -> transform(Position(row, col), this[row, col]).toString() }
            .printValue()
    }
}