import kotlin.time.*

inline operator fun String.invoke(body: TaskPartScope.() -> Unit) {
    println("=== $this ===")
    runCatching { TaskPartScope().apply(body) }
        .onFailure(Throwable::printStackTrace)
    println()
}

class TaskPartScope {
    infix fun <T> T.shouldBe(expected: T) {
        println("Test: $this")
        assertEquals(expected, this)
    }

    fun answer(value: Any?, expected: Any? = null) {
        println("Answer: $value")
        if (expected != null) assertEquals(expected, value)
    }


    fun measureAnswer(expected: Any? = null, calculate: () -> Any?) {
        val value: Any?
        val time = measureTime { value = calculate() }
        println("Answer: $value (done in $time)")
        if (expected != null) {
            assertEquals(expected, value)
        }
    }

    private fun assertEquals(expected: Any?, actual: Any?) {
        check(actual == expected) { "Expected '$expected', but was '$actual'" }
    }
}