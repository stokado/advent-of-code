private const val DAY = "Day05"

private typealias Rule = Pair<Int, Int>
private typealias Update = List<Int>

fun main() {
    val input = readInput(DAY)
    val testInput = readInput("${DAY}_test")

    val part1Expected = 143
    val part1Answer = part1(testInput)

    partResults("Part 1", part1Expected, part1Answer) { part1(input) }

    val part2Expected = 123
    val part2Answer = part2(testInput)

    partResults("Part 2", part2Expected, part2Answer) { part2(input) }
}

private fun part1(input: Pair<Set<Rule>, List<Update>>): Int {
    val (rules, updates) = input
    val comparator = rules.comparator()

    return updates.filter { it.isSorted(comparator) }
        .sumOf { it[it.size / 2] }
}

private fun part2(input: Pair<Set<Rule>, List<Update>>): Int {
    val (rules, updates) = input
    val comparator = rules.comparator()

    return updates.filterNot { it.isSorted(comparator) }
        .map { it.sortedWith(comparator) }
        .sumOf { it[it.size / 2] }
}

private fun Set<Rule>.comparator(): Comparator<Int> = Comparator { o1, o2 ->
    when {
        (o1 to o2) in this -> -1
        (o2 to o1) in this -> 1
        else -> error("Not comparable")
    }
}

private fun <T> List<T>.isSorted(comparator: Comparator<T>): Boolean {
    return asSequence().windowed(2).all { (o1, o2) -> comparator.compare(o1, o2) == -1 }
}

private fun readInput(name: String): Pair<Set<Rule>, List<Update>> {
    val (textRules, textUpdates) = readText(name).split("\n\n")

    val rules = textRules.lines().map { it.splitIntegers("|").toPair() }.toSet()
    val updates = textUpdates.lines().map { it.splitIntegers() }

    return rules to updates
}