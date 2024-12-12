import java.math.*
import java.security.*
import kotlin.io.path.*
import kotlin.time.*

/**
 * Reads lines from the given input txt file.
 */
fun readLines(name: String) = path(name).readLines()

/**
 * Reads input file as a single text
 */
fun readText(name: String) = path(name).readText()

/**
 * Reads values and transforms it
 * @param name path to input data
 * @param transform function to transform input to a type T
 */
fun <T> readLines(name: String, transform: (String) -> T) = readLines(name).map(transform)
fun path(name: String) = Path("src", "$name.txt")

fun String.splitIntegers(vararg delimiters: String = arrayOf(" ", ",", ", ")): List<Int> =
    split(*delimiters).map(String::toInt)

fun String.splitLongs(vararg delimiters: String = arrayOf(" ", ",", ", ")): List<Long> =
    split(*delimiters).map(String::toLong)

fun <T> List<T>.toPair(): Pair<T, T> = get(0) to get(1)

fun <T> List<T>.splitByParity(): Pair<List<T>, List<T>> {
    val first = ArrayList<T>((size + 1) / 2)
    val second = ArrayList<T>((size + 1) / 2)

    for ((index, element) in this.withIndex()) {
        if (index % 2 == 0) {
            first.add(element)
        } else {
            second.add(element)
        }
    }
    return first to second
}

/** More convenient way to print test values. */
fun <T> T.printValue(): T = also(::println)

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun <T> partResults(part: String, expected: T, actual: T, calculation: () -> Any?) {
    println("=== $part ===")
    try {
        check(expected == actual)
    } catch (e: IllegalStateException) {
        println("!!! Wrong answer")
        println("$actual != expected $expected")
    }
    println("Test: $actual")

    val value: Any?
    val time = measureTime { value = calculation() }
    println("Answer: $value (done in $time)")
    println()
}