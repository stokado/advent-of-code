import kotlin.math.*

private const val DAY = "Day01"

fun main() {
    val input = readLines(DAY)
    val testInput = readLines("${DAY}_test")

    partResults("Part 1", 11, part1(testInput)) { part1(input) }
    partResults("Part 2", 31, part2(testInput)) { part2(input) }
}

private fun part1(places: List<String>): Long {
    val teamsPlaces = splitTeamPlaces(places)

    val firstTeamPlaces = teamsPlaces[0].sorted()
    val secondTeamPlaces = teamsPlaces[1].sorted()

    val distances = ArrayList<Long>()

    for (i in firstTeamPlaces.indices) {
        val distance = abs(firstTeamPlaces[i] - secondTeamPlaces[i])
        distances.add(distance)
    }

    return distances.sum()
}

private fun part2(places: List<String>): Long {
    val teamsPlaces = splitTeamPlaces(places)

    val firstTeamPlaces = teamsPlaces[0]
    val secondTeamPlaces = teamsPlaces[1].groupingBy { it }.eachCount()

    var familiarity = 0L

    firstTeamPlaces.forEach { place ->
        val count = secondTeamPlaces.getOrDefault(place, 0)
        familiarity += (place * count)
    }
    return familiarity
}

private fun splitTeamPlaces(places: List<String>): List<List<Long>> {
    val firstTeamPlaces = ArrayList<Long>(places.size)
    val secondTeamPlaces = ArrayList<Long>(places.size)

    for (place in places) {
        val teamsPlaces = place.split("   ")
        firstTeamPlaces.add(teamsPlaces[0].toLong())
        secondTeamPlaces.add(teamsPlaces[1].toLong())
    }
    return listOf(firstTeamPlaces, secondTeamPlaces)
}