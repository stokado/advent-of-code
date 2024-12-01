import java.math.*
import java.security.*
import kotlin.io.path.*
import kotlin.time.*

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun <T> partResults(part: String, expected: T, actual: T, calculation: () -> Any?) {
    println("=== $part ===")
    check(expected == actual)
    println("Test: $actual")

    val value: Any?
    val time = measureTime { value = calculation() }
    println("Answer: $value (done in $time)")
    println()
}