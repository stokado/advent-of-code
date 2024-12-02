import java.math.*
import java.security.*
import kotlin.io.path.*
import kotlin.time.*

/**
 * Reads lines from the given input txt file.
 */
fun readLines(name: String) = path(name).readLines()

/**
 * Reads values and transforms it
 * @param name path to input data
 * @param transform function to transform input to a type T
 */
fun <T> readLines(name: String, transform: (String) -> T) = readLines(name).map(transform)
fun path(name: String) = Path("src", "$name.txt")

fun String.splitIntegers(): List<Int> =
    split(" ").map { it.toInt() }

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